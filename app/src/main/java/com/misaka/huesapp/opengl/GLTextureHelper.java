package com.misaka.huesapp.opengl;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import android.widget.Toast;
import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glGenTextures;

class GLTextureHelper {
   private static final String TAG = "TextureHelper";
    // Load texture from bitmap
    static int[] loadTexture(Context context, Bitmap bitmap) {
        // Create texture object
     	final int[] textureIds = new int[3];
     		glGenTextures(3, textureIds, 0);
     		if (textureIds[0] == 0) {
     			Log.w(TAG, "Could not generate a new OpenGL texture object.");
     			Toast.makeText(context, "Could not generate a new OpenGL texture object.", Toast.LENGTH_SHORT).show();
     			return new int[]{0};
     		}
     		// Setting texture object
     		glActiveTexture(GL_TEXTURE0);
     		glBindTexture(GL_TEXTURE_2D, textureIds[0]);
     		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
     		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
     		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
     		glBindTexture(GL_TEXTURE_2D, 0);
     		return textureIds;
    }
}