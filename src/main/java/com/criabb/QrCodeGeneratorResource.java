package com.criabb;


import java.io.IOException;

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


}
