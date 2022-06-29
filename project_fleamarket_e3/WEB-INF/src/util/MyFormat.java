package util;

import java.text.DecimalFormat;

/*
 * プログラム名：MyFormat
 * プログラムの説明：画面表示のため、各種変数を変換します。
 * 作成者：大仁田 成玄
 * 作成日：2022年6月10日
 */

public class MyFormat {

	/**
	 * DATETIME(YYYY-MM-DD HH:MM:SS.mili)を
	 * 「YYYY年MM月DD日 HH時MM分SS秒」に変換します
	 * @param String datetime
	 * @return String dispDatetime
	 */
	public static String datetimeFormat(String datetime) {

		String dispDatetime = "";

		// substringメソッドを使い、各要素ごとに分割
		String YYYY = datetime.substring(0, 4);
		String MM = datetime.substring(5, 7);
		String DD = datetime.substring(8, 10);

		String HH = datetime.substring(11, 13);
		String MinMin = datetime.substring(14, 16);
		String SS = datetime.substring(17, 19);

		// 頭の0を省くために、一度intに変換している
		int intMM = Integer.parseInt(MM);
		int intDD = Integer.parseInt(DD);

		int intHH = Integer.parseInt(HH);
		int intMinMin = Integer.parseInt(MinMin);
		int intSS = Integer.parseInt(SS);

		// 最後に結合
		dispDatetime = YYYY + "年" + intMM + "月" + intDD + "日" +
						intHH + "時" + intMinMin + "分" + intSS + "秒";

		// 戻り値
		return dispDatetime;

	}

	/**
	 * birthday(YYYY-MM-DD)を
	 * 「YYYY年MM月DD日」に変換します
	 * @param String birthday
	 * @return String dispBirthday
	 */
	public static String birthdayFormat(String birthday) {

		String dispBirthday = "";

		// substringメソッドを使い、各要素ごとに分割
		String YYYY = birthday.substring(0, 4);
		String MM = birthday.substring(5, 7);
		String DD = birthday.substring(8, 10);

		// 頭の0を省くために、一度intに変換している
		int intMM = Integer.parseInt(MM);
		int intDD = Integer.parseInt(DD);

		// 最後に結合
		dispBirthday = YYYY + "年" + intMM + "月" + intDD + "日";

		// 戻り値
		return dispBirthday;
	}

	/**
	 * 引数のpriceを、3桁区切りのString型に変換する
	 * @param int price
	 * @return String price
	 */
	public static String moneyFormat(int price) {

		DecimalFormat df1 = new DecimalFormat(",###");
		return df1.format(price);

	}

	/**
	 * 引数のpriceを、3桁区切り、小数点「.xx」付きのString型に変換する
	 * @param int price
	 * @return String price
	 */
	public static String moneyFormat(double price) {

		DecimalFormat df1 = new DecimalFormat(",###.00");
		return df1.format(price);

	}
}
