package com.example.renz.triangle;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity implements GLSurfaceView.Renderer {

    int _program = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView glView = new GLSurfaceView(this);
        glView.setEGLContextClientVersion(2);
        glView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        glView.setRenderer(this);
        setContentView(glView);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

        //Menurut kaidah yang sesuai, code yg di comment ini diperlukan
        //namun entah kenapa kalo di comment malah bisa jalan
//        String vertexShaderSource = "" +
//                "" +
//                "attribute vec4 position;" +
//                "" +
//                "void main()" +
//                "{" +
//                "     gl_Position = position;" +
//                "}";
//
//        String fragmentShaderSource = "" +
//                "" +
//                "" +
//                "void main()" +
//                "{" +
//                "     gl_FragColor = vec4(0.8, 0.7, 0.6, 1.0);" +
//                "}" ;

//        int vertexShader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
//        GLES20.glShaderSource(vertexShader, vertexShaderSource);
//        GLES20.glCompileShader(vertexShader);
//        String vertexShaderInfoLog = GLES20.glGetShaderInfoLog(vertexShader);
//
//        int fragmentShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
//        GLES20.glShaderSource(fragmentShader, fragmentShaderSource);
//        GLES20.glCompileShader(fragmentShader);
//        String fragmentShaderInfoLog = GLES20.glGetShaderInfoLog(fragmentShader);
//
//        _program = GLES20.glCreateProgram();
//        GLES20.glAttachShader(_program, vertexShader);
//        GLES20.glAttachShader(_program, fragmentShader);
//        GLES20.glBindAttribLocation(_program, 0, "position");
//        GLES20.glLinkProgram(_program);
//        String programLinkLog = GLES20.glGetProgramInfoLog(_program);
//
//        GLES20.glUseProgram(_program);

        GLES20.glClearColor(0.2f, 0.6f, 0.6f, 1.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        float[] geometry = {
          -1.0f, -0.5f, 0.0f, 1.0f,
           1.0f, -0.5f, 0.0f, 1.0f,
           0.0f,  0.4f, 0.0f, 1.0f
        };

        ByteBuffer geometryByteBuffer = ByteBuffer.allocateDirect(geometry.length * 4);
        geometryByteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer geometryBuffer = geometryByteBuffer.asFloatBuffer();
        geometryBuffer.put(geometry);
        geometryBuffer.rewind();

        GLES20.glVertexAttribPointer(0, 4, GLES20.GL_FLOAT, false, 4 * 4, geometryBuffer );
        GLES20.glEnableVertexAttribArray(0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
    }
}
