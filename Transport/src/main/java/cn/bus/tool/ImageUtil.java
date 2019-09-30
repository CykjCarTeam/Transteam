package cn.bus.tool;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// 鐢熸垚  楠岃瘉鐮�  鍜�  楠岃瘉鐮佸浘鐗�  鐨勭被
public final class ImageUtil {
	
	private static final char[] chars = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I' };
	
	private static final int SIZE = 4;      // 楠岃瘉鐮佸浘鐗囦腑鏂囧瓧鐨勪釜鏁�
	private static final int LINES = 3;     // 楠岃瘉鐮佸浘鐗囦腑鐨勫共鎵扮嚎鐨勬暟閲�
	private static final int WIDTH = 80;    // 楠岃瘉鐮佸浘鐗囩殑瀹藉害
	private static final int HEIGHT = 34;   // 楠岃瘉鐮佸浘鐗囩殑楂樺害
	private static final int FONT_SIZE = 19;// 楠岃瘉鐮佸浘鐗囦腑鏂囧瓧鐨勫ぇ灏�

	/**
	 * 鐢熸垚楠岃瘉鐮佸拰楠岃瘉鐮佸浘鐗囩殑鏂规硶锛屽苟灏佽鍦∕ap涓�� 
	 * 
	 * 鍏朵腑Map鐨刱ey鏄獙璇佺爜锛孧ap鐨剉alue鏄獙璇佺爜鍥剧墖銆�
	 */
	public static Map<String, BufferedImage> createImage() {
		
		StringBuffer sb = new StringBuffer();
		
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		Graphics graphic = image.getGraphics();
		graphic.setColor(Color.LIGHT_GRAY);  
		graphic.fillRect(0, 0, WIDTH, HEIGHT);
		Random ran = new Random();
		// 鐢婚殢鏈哄瓧绗�
		for (int i = 1; i <= SIZE; i++) {
			int r = ran.nextInt(chars.length);
			graphic.setColor(getRandomColor());
			graphic.setFont(new Font(null, Font.BOLD + Font.ITALIC, FONT_SIZE));
			graphic.drawString(chars[r] + "", (i - 1) * WIDTH / SIZE,
					HEIGHT / 2);
			sb.append(chars[r]);// 灏嗗瓧绗︿繚瀛橈紝瀛樺叆Session
		}
		// 鐢诲共鎵扮嚎
		for (int i = 1; i <= LINES; i++) {
			graphic.setColor(getRandomColor());
			graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT), ran
					.nextInt(WIDTH), ran.nextInt(HEIGHT));
		}
		Map<String, BufferedImage> map = new HashMap<String, BufferedImage>();
		map.put(sb.toString(), image);
		return map;
	}

	
	
	/**
	 * 灏嗗浘鐗囦紶鍏ヨ緭鍏ユ祦鐨勬柟娉�
	 */
	public static InputStream getInputStream(BufferedImage image)
			throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
		encoder.encode(image);
		byte[] imageBts = bos.toByteArray();
		InputStream in = new ByteArrayInputStream(imageBts);
		return in;
	}

	
	// 浜х敓闅忔満棰滆壊鐨勬柟娉�
	private static Color getRandomColor() {
		
		Random ran = new Random();   // 闅忔満鍑芥暟
		// 浜х敓闅忔満鐨凴GB棰滆壊
		Color color = new Color(ran.nextInt(256), ran.nextInt(256), ran
				.nextInt(256));
		return color;
	}
}