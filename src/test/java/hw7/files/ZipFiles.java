package hw7.files;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

public class ZipFiles {

    private final ClassLoader cl = ZipFiles.class.getClassLoader();

    @Test
    public void zipFilesTest() throws Exception {
        ZipFile zf = getZipFile("testFiles.zip");

        ZipEntry pdfEntry = zf.getEntry("sample.pdf");
        try (InputStream pdfStream = zf.getInputStream(pdfEntry)) {
            PDF pdf = new PDF(pdfStream);
            assertThat(pdf.title).isEqualTo("PDF Form Example");
        }

        ZipEntry xlsEntry = zf.getEntry("XLSX_DATA.xlsx");
        try (InputStream xlsStream = zf.getInputStream(xlsEntry)) {
            XLS xls = new XLS(xlsStream);
            assertThat(xls.excel.getSheetAt(0).getRow(2).getCell(1).getStringCellValue()).isEqualTo("Dina");
        }

        ZipEntry csvEntry = zf.getEntry("CSV_DATA.csv");
        try (InputStream csvStream = zf.getInputStream(csvEntry)) {
            CSVReader csv = new CSVReader(new InputStreamReader(csvStream));
            List<String[]> list = csv.readAll();
            assertThat(list)
                    .hasSize(301)
                    .contains(
                            new String[]{"1", "Reeva", "Weathers", "rweathers0@github.com", "Female", "53.218.20.175"},
                            new String[]{"11", "Kahlil", "Twidale", "ktwidalea@infoseek.co.jp", "Male", "248.51.112.74"});
        }
    }

    private ZipFile getZipFile(String name) throws IOException, URISyntaxException {
        return new ZipFile(new File(requireNonNull(cl.getResource(name)).toURI()));
    }
}
