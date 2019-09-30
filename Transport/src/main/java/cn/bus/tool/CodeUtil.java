package cn.bus.tool;


import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class CodeUtil {

	private static final int BLACK = 0xff000000;
	private static final int WHITE = 0xFFFFFFFF;

	/**
	 * @param
	 */
	public BufferedImage getBufferedImage() throws Exception{
		//随机生成字符串
		Map<String, BufferedImage> img= ImageUtil.createImage();
		String code=img.keySet().iterator().next();
		//生成二维码图片，同时放入上面的字符串
		Map<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		//设置编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

		//生成一个矩阵
		BitMatrix bitMatrix = new MultiFormatWriter().encode("", BarcodeFormat.QR_CODE, 100, 100, hints);

		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image1.setRGB(x, y, bitMatrix.get(x, y) == true ? BLACK : WHITE);
			}
		}

		return image1;
	}
	public static void main(String[] args) {
		CodeUtil test = new CodeUtil();
		String filePostfix="png";//二维码图片格式
		File file = new File("F://test_QR_CODE."+filePostfix);//二维码图片存放路径
		//随机生成字符串
		Map<String, BufferedImage> img= ImageUtil.createImage();
		String code=img.keySet().iterator().next();
		//生成二维码图片，同时放入上面的字符串
		test.encode(code, file,filePostfix, BarcodeFormat.QR_CODE, 100, 100);
		//读取二维码图片内容
		test.decode(file);
	}

	/**
	 *  生成QRCode二维码<br>
	 *  在编码时需要将com.google.zxing.qrcode.encoder.Encoder.java中的<br>
	 *  static final String DEFAULT_BYTE_MODE_ENCODING = "ISO8859-1";<br>
	 *  修改为UTF-8，否则中文编译后解析不了<br>
	 * @param contents 二维码的内容
	 * @param file 二维码保存的路径，如：C://test_QR_CODE.png
	 * @param filePostfix 生成二维码图片的格式：png,jpeg,gif等格式
	 * @param format qrcode码的生成格式
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @param
	 */
	public void encode(String contents, File file,String filePostfix, BarcodeFormat format, int width, int height) {
		try {
			Map<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			//设置编码格式
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			//生成一个矩阵
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, format, width, height, hints);
			//生成二维码图片
			writeToFile(bitMatrix, filePostfix, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成二维码图片<br>
	 *
	 * @param matrix
	 * @param format
	 *            图片格式
	 * @param file
	 *            生成二维码图片位置
	 * @throws IOException
	 */
	public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
		//生成图片
		BufferedImage image = toBufferedImage(matrix);
		//输出图片
		ImageIO.write(image, format, file);
	}

	/**
	 * 生成二维码内容<br>
	 *
	 * @param matrix
	 * @return
	 */
	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) == true ? BLACK : WHITE);
			}
		}
		return image;
	}

	/**
	 * 解析QRCode二维码
	 */
	@SuppressWarnings("unchecked")
	public Map decode(File file) {
		Map map=new HashMap();
		try {
			BufferedImage image;
			try {
				image = ImageIO.read(file);
				if (image == null) {
					System.out.println("Could not decode image");
				}
				//保存图片数据流
				map.put("image",image);

				LuminanceSource source = new BufferedImageLuminanceSource(image);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
				Result result;
				@SuppressWarnings("rawtypes")
				Hashtable hints = new Hashtable();
				//解码设置编码方式为：utf-8
				hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
				result = new MultiFormatReader().decode(bitmap, hints);
				String resultStr = result.getText();
				System.out.println("解析后内容：" + resultStr);
				//保存解析字符串
				map.put("resultStr",resultStr);
			} catch (IOException ioe) {
				System.out.println(ioe.toString());
			} catch (ReaderException re) {
				System.out.println(re.toString());
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return map;
	}
}
