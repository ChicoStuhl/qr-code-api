package com.criabb.service;

import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.criabb.model.QrCodeGeneratorRequest;
import com.criabb.model.QrCodeGeneratorResponse;
import com.criabb.model.qrCodeEntity;
import com.criabb.model.qrCodeTableEntity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@RequestScoped
public class QrCodeGeneratorService {

    @Inject
    qrCodeTableEntity qrCodeTableEntity;

    private String outPutQRLocation;
    private static final String charset = "UTF-8";
    private static final String strDateFormat = "ddMMyyyyHHmmss";

    @Transactional
    public QrCodeGeneratorResponse generateQrCode(QrCodeGeneratorRequest qrCodeGeneratorRequest) throws WriterException, IOException {
        QrCodeGeneratorResponse qrCodeGeneratorResponse = new QrCodeGeneratorResponse();

        qrCodeGeneratorResponse.setLocation(processQrCode(qrCodeGeneratorRequest.getQrCodeText(), prepareOutputQrCodeLocation(), charset, 400, 400));
        return qrCodeGeneratorResponse;
    }

    private String processQrCode(String qrCodeText, String location, String charset, int width, int height) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeText.getBytes(charset), charset), BarcodeFormat.QR_CODE, width, height);
        System.out.println(qrCodeText);
        qrCodeTableEntity.insert(qrCodeText, location);
        MatrixToImageWriter.writeToPath(matrix, "png", Path.of(location));
        return location;
    }

    private String prepareOutputQrCodeLocation() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
        outPutQRLocation = "./imagens/"+ formattedDate + ".png";
        return outPutQRLocation;
    }

    @Transactional
    public List<qrCodeEntity> getAllQrCode() {
        return qrCodeTableEntity.findAllEntity();
    }
}
