import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

import java.io.File;
import java.io.IOException;

public class Pdf {

    public static void main(String [] args){
        String inputFilePath = "/home/yeison/Documentos/Monica proyectos/pdf-password/src/main/java/input.pdf"; // Ruta del archivo PDF de entrada
        String outputFilePath = "/home/yeison/Documentos/Monica proyectos/pdf-password/src/main/java/output.pdf"; // Ruta del archivo PDF de salida
        String ownerPassword = "owner123"; // Contraseña del propietario
        String userPassword = "user123"; // Contraseña del usuario

        try (PDDocument document = Loader.loadPDF(new File(inputFilePath))) {
            AccessPermission accessPermission = new AccessPermission();
            StandardProtectionPolicy spp = new StandardProtectionPolicy(ownerPassword, userPassword, accessPermission);
            spp.setEncryptionKeyLength(128);
            spp.setPermissions(accessPermission);
            document.protect(spp);
            document.save(outputFilePath);
            System.out.println("PDF protegido con contraseña guardado en: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo PDF: " + e.getMessage());
        }
    }
    }

