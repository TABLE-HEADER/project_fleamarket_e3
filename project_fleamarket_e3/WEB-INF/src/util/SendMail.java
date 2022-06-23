package util;

/*
 * プログラム名：SendMail
 * プログラムの説明：メールの作成、送信を行う
 * 作成者：大仁田 成玄
 * 作成日：2022年6月7日
 */

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	// 各種必要な変数の用意
	private final Properties props = System.getProperties();
	private final Session session = Session.getInstance(
			props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					//メールサーバにログインするメールアドレスとパスワードを設定
					return new PasswordAuthentication("system.project.team39@kanda-it-school-system.com", "ySWWHAiL2I");
				}
			}
		);
	private final MimeMessage mimeMessage = new MimeMessage(session);
	private String codeType = null;
	private String text = "";

	/**
	 * コンストラクタ
	 * ホスト名、IPアドレス、標準文字コードを設定
	 */
	public SendMail() {
		// SMTPサーバのアドレスを指定（今回はxserverのSMTPサーバを利用）
		props.put("mail.smtp.host", "sv5215.xserver.jp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.debug", "true");
		this.codeType = "iso-2022-jp";
	}

	/**
	 * 文字コードを設定
	 * @param String codeType
	 */
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	/**
	 * 送信元メールアドレスと送信者名を指定
	 * @param String senderAddress
	 * @param String senderName
	 */
	public void setFrom(String senderAddress, String senderName) {
		try {
			mimeMessage.setFrom(new InternetAddress(senderAddress, senderName, this.codeType));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 送信先メールアドレスを指定
	 * @param String recieverAddress
	 */
	public void setRecipients(String recieverAddress) {
		try {
			mimeMessage.setRecipients(Message.RecipientType.TO, recieverAddress);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * メールの件名を指定
	 * @param String subject
	 */
	public void setSubject(String subject) {
		try {
			mimeMessage.setSubject(subject, this.codeType);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * メール本文を作る
	 * 引数のtextの先頭に、改行を入れたうえで本文を作っている
	 * @param String content
	 */
	public void setText(String text) {
		this.text += "\n" + text;

		try {
			mimeMessage.setText(this.text, this.codeType);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * メールの送信をおこなう
	 * 送信が完了したらコンソール画面に送信成功のメッセージが出力され、
	 * 送信に失敗した場合は送信失敗のメッセージが出力される
	 */
	public void sendMail() {
		try {
			mimeMessage.setHeader("Content-Type", "text/plain; charset=" + this.codeType);

			mimeMessage.setSentDate(new Date());

			Transport.send(mimeMessage);

			System.out.println("送信に成功しました。");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("送信に失敗しました。");
		}
	}
}
