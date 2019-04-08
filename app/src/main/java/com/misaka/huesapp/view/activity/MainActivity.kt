package com.misaka.huesapp.view.activity

import ai.fritz.core.Fritz
import ai.fritz.core.FritzOnDeviceModel
import ai.fritz.fritzvisionstylepaintings.PaintingStyles
import ai.fritz.vision.FritzVision
import ai.fritz.vision.FritzVisionImage
import ai.fritz.vision.styletransfer.FritzStyleResolution
import ai.fritz.vision.styletransfer.FritzVisionStylePredictorOptions

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.opengl.GLSurfaceView
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Size
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.misaka.huesapp.AppConstant
import com.misaka.huesapp.R
import com.misaka.huesapp.contract.MainContract
import com.misaka.huesapp.model.Filter
import com.misaka.huesapp.model.PresetHelper
import com.misaka.huesapp.model.Renderer
import com.misaka.huesapp.model.StyleHelper
import com.misaka.huesapp.opengl.GLRenderer
import com.misaka.huesapp.presenter.MainPresenter
import com.misaka.huesapp.view.Base
import com.misaka.huesapp.view.BaseImpl
import com.misaka.huesapp.view.ResizeUtils
import com.misaka.huesapp.view.fragment.StyleFragment
import com.misaka.huesapp.view.fragment.FilterFragment
import com.misaka.huesapp.view.fragment.PresetFragment
import com.zomato.photofilters.FilterPack
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tool_bar.*
import java.util.*


class MainActivity : AppCompatActivity(), MainContract.View {

    init { System.loadLibrary(AppConstant.NATIVE_LIBRATY_NAME) }

    // Delegate
    class Del(b: Base) : Base by b

    private var presenter: MainPresenter? = null
    private var isFabMenuOpen: Boolean = false
    private var isGlSurfaceViewSet: Boolean = false
    private var glSurfaceView: GLSurfaceView? = null
    private var glRenderer: GLRenderer? = null
    private var bitmap: Bitmap? = null
    private var imageView: ImageView? = null
    private var fritzModel: FritzOnDeviceModel? = null

    private val path: String = Environment.getExternalStorageDirectory().absolutePath

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkES2Support()
        setContentView(R.layout.activity_main)
        setSupportActionBar(tool_bar as Toolbar?)

        // Init fritz api
        Fritz.configure(this, AppConstant.FRITZ_API_KEY)

        StyleHelper.createStylePreview(this)

        presenter = MainPresenter(this)
    }

    override fun init() {
        // Open image button
        open_button.setOnClickListener { presenter?.openImage()
        }

        // Check button
        chackmarkActionButton.setOnClickListener{
            presenter?.startFabMenuAnimation()
        }

        // TODO: Add SnackBar
        // Save button
        saveActionButton.setOnClickListener {
            presenter?.saveImage()
        }

        // Reset button
        reset_button.setOnClickListener {
            glRenderEvent(ArrayList())
        }

        // Info button
        info_image_button.setOnClickListener {
            // TODO: Implement it
        }

        // Share button
        shareActionButton.setOnClickListener {
            presenter?.shareImage()
        }

        // Navigation menu
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_filters -> {
                    openFragment(FilterFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_presets -> {
                    openFragment(PresetFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_etc -> {
                    openFragment(StyleFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun animateFabMenu() {
        if (isFabMenuOpen) closeFabMenu() else showFabMenu()
    }

    private fun closeFabMenu() {
        isFabMenuOpen = false
        saveActionButton.animate().translationY(0F)
        shareActionButton.animate().translationY(0F)
    }

    private fun showFabMenu() {
        isFabMenuOpen = true
        saveActionButton.animate().translationY(-resources.getDimension(R.dimen.standard_60))
        shareActionButton.animate().translationY(-resources.getDimension(R.dimen.standard_120))
    }

    override fun showOpenImageDialog() {
        Del(BaseImpl(this, this)).showOpenImageDialog()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            when (requestCode) {
                AppConstant.REQUEST_IMAGE_CAPTURE -> bitmap = data.extras?.get("data") as Bitmap
                AppConstant.GALLERY_REQUEST ->  bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, data.data)
            }
            PresetHelper.createPreviewPreset(getLowQualityBitmap(bitmap!!), this)
            presenter?.setViewsVisible()
            Renderer.setImage(this.bitmap!!)
            createGLSurfaceView()
            openFragment(FilterFragment.newInstance())
        }
    }

    private fun getLowQualityBitmap(bitmap: Bitmap): Bitmap {
        return Bitmap.createScaledBitmap(bitmap, bitmap.width /  10, bitmap.height / 10, false)
    }

    override fun setVisibility() {
        navigation.visibility = View.VISIBLE
        reset_button.visibility = View.VISIBLE
        info_image_button.visibility = View.VISIBLE
        chackmarkActionButton.visibility = View.VISIBLE
        saveActionButton.visibility = View.VISIBLE
        shareActionButton.visibility = View.VISIBLE
    }

    private fun createGLSurfaceView() {
        if (isGlSurfaceViewSet) glSurfaceView?.visibility = View.GONE else isGlSurfaceViewSet = true

        glSurfaceView = GLSurfaceView(applicationContext)
        glSurfaceView?.setEGLContextClientVersion(2)
        glRenderer = GLRenderer(applicationContext)
        glRenderer?.setBitmap(Renderer.getImage())
        glSurfaceView?.setRenderer(glRenderer)
        glSurfaceView?.renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY

        ResizeUtils.resizeView(
            gl_layout,
            Renderer.getImage()!!.height.toFloat(),
            Renderer.getImage()!!.width.toFloat(),
            applicationContext
        )

        gl_layout.addView(glSurfaceView)
    }

    override fun setDefaultUIPosition() {

    }

    fun glRenderEvent(filters: ArrayList<Filter>) {
        glSurfaceView?.queueEvent {
            glRenderer?.setFilters(filters)
            glSurfaceView!!.requestRender()
        }
    }

    private fun checkES2Support() {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val configurationInfo = (activityManager).deviceConfigurationInfo
        if (!(configurationInfo.reqGlEsVersion >= 0x20000
                    || Build.FINGERPRINT.startsWith("generic")
                    || Build.FINGERPRINT.startsWith("unknown")
                    || Build.MODEL.contains("google_sdk")
                    || Build.MODEL.contains("Emulator")
                    || Build.MODEL.contains("Android SDK built for x86"))
        ) {
            Toast.makeText(this, "OpenGL ES 2.0 is not supported ):", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun saveImageFromGLRenderer() {
        if (Del(BaseImpl(this, this)).checkWriteDataPermission()) {
            Renderer.saveImage(glRenderer?.bmp!!, path,100)
        }
        else { /*pass?*/}
    }

    // TODO: Use FileProvider instead of saving(?)(API >= 24)
    override fun startShareIntent() {
        if (Del(BaseImpl(this, this)).checkWriteDataPermission()) {
            val uri = Renderer.saveImage(glRenderer?.bmp!!, path,100)
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "image/jpeg"
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            startActivity(Intent.createChooser(intent, "Share"))
        }
    }

    // TODO: Вынести из MainActivity
    fun setPreset(presetID: Int){
        var presets = FilterPack.getFilterPack(this)
        bitmap = glRenderer?.bmp
        when(presetID){
            0  -> FilterPack.getAdeleFilter(this).processFilter(bitmap)
            1  -> FilterPack.getAmazonFilter(this).processFilter(bitmap)
            2  -> FilterPack.getAprilFilter(this).processFilter(bitmap)
            3  -> FilterPack.getAudreyFilter(this).processFilter(bitmap)
            4  -> FilterPack.getAweStruckVibeFilter(this).processFilter(bitmap)
            5  -> FilterPack.getBlueMessFilter(this).processFilter(bitmap)
            6  -> FilterPack.getClarendon(this).processFilter(bitmap)
            7  -> FilterPack.getCruzFilter(this).processFilter(bitmap)
            8  -> FilterPack.getHaanFilter(this).processFilter(bitmap)
            9  -> FilterPack.getLimeStutterFilter(this).processFilter(bitmap)
            10 -> FilterPack.getMarsFilter(this).processFilter(bitmap)
        }
        imageView = ImageView(this)
        imageView!!.setImageBitmap(bitmap)
        gl_layout.addView(imageView)
    }

    // TODO: Вынести из MainActivity
    fun setStyle(styleID: Int) {
        when(styleID){
            0  -> fritzModel = PaintingStyles.BICENTENNIAL_PRINT
            1  -> fritzModel = PaintingStyles.FEMMES
            2  -> fritzModel = PaintingStyles.HEAD_OF_CLOWN
            3  -> fritzModel = PaintingStyles.HORSES_ON_SEASHORE
            4  -> fritzModel = PaintingStyles.KALEIDOSCOPE
            5  -> fritzModel = PaintingStyles.PINK_BLUE_RHOMBUS
            6  -> fritzModel = PaintingStyles.POPPY_FIELD
            7  -> fritzModel = PaintingStyles.RITMO_PLASTICO
            8  -> fritzModel = PaintingStyles.STARRY_NIGHT
            9  -> fritzModel = PaintingStyles.THE_SCREAM
            10 -> fritzModel = PaintingStyles.THE_TRAIL
        }

        val options =  FritzVisionStylePredictorOptions.Builder()
            .imageResolution(glRenderer?.bmp!!.width, glRenderer?.bmp!!.height)
            .build()

        val predictor = FritzVision.StyleTransfer.getPredictor(fritzModel, options)
        val fritzImage = FritzVisionImage.fromBitmap(glRenderer?.bmp)
        val fritzStyleResult = predictor.predict(fritzImage)

        bitmap = fritzStyleResult.toBitmap(Size(glRenderer?.bmp!!.width, glRenderer?.bmp!!.height))

        imageView = ImageView(this)
        imageView!!.setImageBitmap(bitmap)

        gl_layout.addView(imageView)
    }
}