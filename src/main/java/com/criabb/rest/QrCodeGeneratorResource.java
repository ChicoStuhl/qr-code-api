package com.criabb.rest;


import java.io.IOException;
import java.util.List;

import com.criabb.model.QrCodeGeneratorRequest;
import com.criabb.model.QrCodeGeneratorResponse;
import com.criabb.model.qrCodeEntity;
import com.criabb.service.QrCodeGeneratorService;
import com.google.zxing.WriterException;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class QrCodeGeneratorResource {

    @Inject
    QrCodeGeneratorService qrCodeGeneratorService;

    @Path("generate")
    @POST
    public QrCodeGeneratorResponse generateQrCode(QrCodeGeneratorRequest qrCodeGeneratorRequest) throws WriterException, IOException {
        return qrCodeGeneratorService.generateQrCode(qrCodeGeneratorRequest);
    }

    @Path("getQrCode")
    @POST
    public List<qrCodeEntity> getAllQrCode(){
        return qrCodeGeneratorService.getAllQrCode();
    }


}
