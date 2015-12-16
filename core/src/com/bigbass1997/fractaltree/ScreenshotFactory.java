package com.bigbass1997.fractaltree;

import java.nio.ByteBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bigbass1997.fractaltree.world.Tree;

/**
 * @see <a href="https://github.com/libgdx/libgdx/wiki/Take-a-Screenshot">https://github.com/libgdx/libgdx/wiki/Take-a-Screenshot</a>
 */
public class ScreenshotFactory {
	
	public static void saveScreen(Tree tree){
		try{
			String d = ".", format = ".png";
			String filename = "saved-trees/tree" + d + System.nanoTime() + format;
			
            FileHandle fh = new FileHandle(filename);
            
            int w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
            
            Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(0, 0, w, h);
            
            ByteBuffer pixels = pixmap.getPixels();
            int numBytes = w * h * 4;
            byte[] lines = new byte[numBytes];
            int numBytesPerLine = w * 4;
            for (int i = 0; i < h; i++) {
                pixels.position((h - i - 1) * numBytesPerLine);
                pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
            }
            pixels.clear();
            pixels.put(lines);
            pixels.clear();
            
            PixmapIO.writePNG(fh, pixmap);
            pixmap.dispose();
        }catch (Exception e){     
        	e.printStackTrace();
        	return;
        }
	}
}
