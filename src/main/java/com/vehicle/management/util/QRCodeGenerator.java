package com.vehicle.management.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.vehicle.management.entity.CustomerMaster;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QRCodeGenerator {
	
	public static byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
		log.info("<<start>> In method getQRCodeImage <<start>>");
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

		ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
		MatrixToImageConfig con = new MatrixToImageConfig(0xFF000002, 0x00000000);

		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, con);
		byte[] pngData = pngOutputStream.toByteArray();
		log.info("<<end>> In method getQRCodeImage <<end>>");
		return pngData;
	}

	public static byte[] getCode128Image(String text, int width, int height) throws WriterException, IOException {
		log.info("<<start>> In method getCode128Image <<start>>");
		Code128Writer code128Writer = new Code128Writer();
		BitMatrix bitMatrix = code128Writer.encode(text, BarcodeFormat.CODE_128, width, height);

		ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
		MatrixToImageConfig con = new MatrixToImageConfig(0xFF000002, 0x00000000);

		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, con);
		byte[] pngData = pngOutputStream.toByteArray();
		log.info("<<end>> In method getCode128Image <<end>>");
		return pngData;
	}

	public static String createUniqueNoForQrCode(CustomerMaster customerMaster) {
		log.info("<<start>> In method createUniqueNoForQrCode <<start>>");
		StringBuilder uniqueNumber = new StringBuilder();
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String number = "0123456789";
		if (customerMaster.getName().toLowerCase().equals("inward")) {
			uniqueNumber.append("I");
		} else {
			uniqueNumber.append("O");
		}
		log.info("uniqueNumber First" +uniqueNumber);
		uniqueNumber.append(generateRandom(alphabet, 3));
		log.info("generateRandom" +uniqueNumber);
//		uniqueNumber.append(getDigitsFromLast(transactions.getDeliveryOrderNumber(), 2));
//		log.info("getDigitsFromLast" +uniqueNumber);
		uniqueNumber.append(generateRandom(number, 3));
		log.info("generateRandom" +uniqueNumber);
//		uniqueNumber.append(getDigitsFromStart(transactions.getVehicleMaster().getVehicleRegistrationNumber(),5));
//		log.info("getVehicleRegistrationNumber" +uniqueNumber);
//		uniqueNumber.append(customerMaster.getCommodityMaster().getId());
//		log.info("uniqueNumber LAST" +uniqueNumber);
		return new String(uniqueNumber).toUpperCase();
	}

	public static StringBuilder generateRandom(String word, int length) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= length; i++) {
			int index = random.nextInt(word.length());
			char randomChar = word.charAt(index);
			sb.append(randomChar);
		}
		return sb;
	}

	public static StringBuilder getDigitsFromLast(String word, int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = length; i >= 1; i--) {
			sb.append(word.charAt(word.length() - i));
		}
		return sb;
	}

	public static StringBuilder getDigitsFromStart(String word, int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(word.charAt(i));
		}
		return sb;
	}




}
