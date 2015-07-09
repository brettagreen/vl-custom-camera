package org.apache.cordova.camera;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.hardware.Camera;

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    
    private SurfaceHolder mHolder;
    public Camera camera = null;
    
    public CameraSurfaceView(Context context) {
        super(context);
        
        mHolder = getHolder();
        mHolder.addCallback(this);
       
    }
    
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Camera.Parameters params = camera.getParameters();
        params.setPreviewSize(width, height);
        camera.setParameters(params);
        camera.startPreview();
    }
    
    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open();
        
        try {
            camera.setPreviewDisplay(mHolder);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
        camera = null;
        
    }
    
    public void capture(Camera.PictureCallback jpegHandler) {
        camera.takePicture(null, null, jpegHandler);
    }
    
}
