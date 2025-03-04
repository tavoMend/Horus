package Reportes;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.DBConexion;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Gabriel
 */
public class ReporteGasto {
    
    public static void reporte() {

        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Gastos");

        try {
           // InputStream is = new FileInputStream("src/img/logo.png");
            //byte[] bytes = IOUtils.toByteArray(is);
           // int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
           // is.close();

            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();

            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(1);
           // Picture pict = draw.createPicture(anchor, imgIndex);
            //pict.resize(1, 3);

            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);

            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("Reporte de Gastos");

            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));

            String[] cabecera = new String[]{"Código de Gasto", "Fecha de Gasto", "Monto", "Descripción", "Código Método de Pago", "Código Estado", "Código Categoria"};

            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);

            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);

            Row filaEncabezados = sheet.createRow(4);

            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEncabezado = filaEncabezados.createCell(i);
                celdaEncabezado.setCellStyle(headerStyle);
                celdaEncabezado.setCellValue(cabecera[i]);
            }
            
             for (int i = 0; i < cabecera.length; i++) {
                sheet.autoSizeColumn(i);
            }

            DBConexion con = new DBConexion();
            PreparedStatement ps;
            ResultSet rs;
            Connection conn = con.establecerConexion();

            try {
                int numFilaDatos = 5;

                CellStyle datosEstilo = book.createCellStyle();
                datosEstilo.setBorderBottom(BorderStyle.THIN);
                datosEstilo.setBorderLeft(BorderStyle.THIN);
                datosEstilo.setBorderRight(BorderStyle.THIN);
                datosEstilo.setBorderTop(BorderStyle.THIN);
                
                datosEstilo.setAlignment(HorizontalAlignment.CENTER);
                datosEstilo.setVerticalAlignment(VerticalAlignment.CENTER);


                ps = conn.prepareStatement("SELECT codigoGasto, fechaGasto, monto, descripcion, id_Metodo_Pago, id_Estado, id_Categoria FROM gastos");
                rs = ps.executeQuery();

                int numCol = rs.getMetaData().getColumnCount();

                while (rs.next()) {
                    Row filaDatos = sheet.createRow(numFilaDatos);

                    for (int a = 0; a < numCol; a++) {
                        Cell celdaDatos = filaDatos.createCell(a);
                        celdaDatos.setCellStyle(datosEstilo);
                        celdaDatos.setCellValue(rs.getString(a + 1));
                    }

                    numFilaDatos++;
                }
                sheet.autoSizeColumn(0);
                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.autoSizeColumn(3);
                sheet.autoSizeColumn(4);

                sheet.setZoom(100);
                String fileName = "gastos";
                String home = System.getProperty("user.home");
                File file = new File(home + "/Downloads/" + fileName + ".xlsx");
                FileOutputStream fileOut = new FileOutputStream(file);
                book.write(fileOut);
                fileOut.close();
                Desktop.getDesktop().open(file);
                JOptionPane.showMessageDialog(null, "Reporte Generado");

            } finally {
                conn.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReporteIngreso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(ReporteIngreso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
