package util;

/*
 * ---- 利用例 ----
 *
 * ・ローカルに存在する画像ファイルをオブジェクトに格納
 * String path = getServletContext().getRealPath("./file/icon.png").toString();
 * BufferedImage img = ImageConvert.readImage(path);
 * byte[] bytes = ImageConvert.imageToByte(img);
 *
 * ・JSPからBase64情報を取得してユーザー情報とテーブルを更新
 * String str = request.getParameter("icon");
 * byte[] bytes;
 * if(!str.equals("")) {
 *		bytes = Base64.getDecoder().decode(str);
 * }
 * else {
 * 		bytes = user.getIcon();
 * }
 * user_new = new User(…, user.getDate());
 * objDao.insert(user);
 *
 * ・ユーザー情報をテーブルに格納
 * con = UserDAO.getConnection();
 * smt = con.prepareStatement(sql);
 * smt.setBytes(1, user.getIcon());
 * smt.executeUpdate();
 *
 * ・ユーザー情報をテーブルから取得
 * user.setIcon(ImageConvert.getByteFromResult(rs, "icon"));
 *
 * ・バイトデータをJSPに表示
 * <img src="data:image/png;base64,<%=ImageConvert.writeImage(ImageConvert.byteToImage(user_side.getIcon()), request, response) %>>
 *
 */

import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.User;
import dao.UserDAO;

public class ImageConvert {

	// ファイル名をBufferedImage形式に変換
	public static BufferedImage readImage(String filename) {

		BufferedImage img;

		try {
			File file = new File(filename);
			img = ImageIO.read(file);
		} catch (IOException e) {
				img = new BufferedImage(0, 0, 0);
		}
		return img;

	}

	// part形式をBufferedImage形式に変換
	public static BufferedImage readImage(Part part) {

		BufferedImage img;

		try {
			InputStream is = part.getInputStream();
			img = ImageIO.read(is);
		} catch (IOException e) {
				img = new BufferedImage(0, 0, 0);
		}
		return img;

	}

	// BufferedImage形式をPNG形式バイトデータに変換
	public static byte[] imageToByte(BufferedImage img) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(baos);
		img.flush();
		ImageIO.write(img, "png", bos);
		return baos.toByteArray();

	}

	// BufferedImage形式を任意形式バイトデータに変換
	public static byte[] imageToByte(BufferedImage img, String format) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(baos);
		img.flush();
		ImageIO.write(img, format, bos);
		return baos.toByteArray();

	}

	// バイトデータを画像形式に変換
	public static BufferedImage byteToImage(byte[] bytes) throws IOException {

		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		BufferedImage img = ImageIO.read(bais);
		return img;

	}

	// BufferedImage形式のresponseへの書き込み（png）
	public static void writeImage(HttpServletResponse response, BufferedImage img) {

		try {
			response.setContentType("image/png");
			OutputStream outputStream = response.getOutputStream();
			ImageIO.write(img, "png", outputStream);
			outputStream.flush();
		}catch(IOException e) {
			System.out.println(e);
		}
	}

	// BufferedImage形式のresponseへの書き込み（任意形式）
	public static void writeImage(HttpServletResponse response, BufferedImage img, String format) {

		try {
			response.setContentType("image/" + format);
			OutputStream outputStream = response.getOutputStream();
			ImageIO.write(img, format, outputStream);
			outputStream.flush();
		}catch(IOException e) {
			System.out.println(e);
		}
	}

	// アウトプットソースのBase64形式を変換
	public static String writeImage(BufferedImage img, HttpServletRequest request ,HttpServletResponse response) {

		try {

			ByteArrayOutputStream output = new ByteArrayOutputStream();
			ImageIO.write(img, "png", output);
			String imageAsBase64 = Base64.getEncoder().encodeToString(output.toByteArray());
			return imageAsBase64;

		}catch(IOException e) {
			System.out.println(e);
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// SQLのResultSetからバイトデータを抽出
	public static byte[] getByteFromResult(ResultSet rs, String column) {

		InputStream is;

		try {

			is = rs.getBinaryStream(column);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] bs = new byte[1024];

			int size = 0;
			while((size = is.read(bs)) != -1 ){
				baos.write(bs, 0, size);
			}

			return baos.toByteArray();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return null;
	}

	// BufferedImageのRGBをランダムに変更
	public static void changeRGB(BufferedImage img) {

		int w = img.getWidth();
		int h = img.getHeight();

		int r = (int)(Math.random() * 255);
		int g = (int)(Math.random() * 255);
		int b = (int)(Math.random() * 255);
		int rgb =  0xff000000 | r << 16 | g << 8 | b;

		for(int y = 0; y < h; y++){
			for(int x = 0; x < w; x++){

				if(img.getRGB(x, y) != 0xffffffff) {
					img.setRGB(x, y, rgb);
				}

			}
		}

	}


}
