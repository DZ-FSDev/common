import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

/**
 * Defines a list of input PDF files to be merged (inputFiles), and the output
 * merged PDF file (outputFile). Instantiates an instance of PDFMergerUtility
 * from Apache PDFBox, add the input PDF files to be merged using addSource()
 * method, set the output merged PDF file using setDestinationFileName()
 * method, and finally call mergeDocuments() method to perform the PDF merging.
 * 
 * @version 0.0.1
 * @author DZ-FSDev
 */
public class PDFMerger {
    /**
     * Main method to merge PDF files.
     *
     * @param args Command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        try {
            // Input PDF files to be merged
            List<String> inputFiles = new ArrayList<>();
            inputFiles.add("input1.pdf");
            inputFiles.add("input2.pdf");
            inputFiles.add("input3.pdf");

            // Output merged PDF file
            String outputFile = "merged.pdf";

            // Create PDFMergerUtility object
            PDFMergerUtility merger = new PDFMergerUtility();

            // Set memory usage setting
            merger.setMemoryUsageSetting(MemoryUsageSetting.setupMainMemoryOnly());

            // Add input PDF files to be merged
            for (String inputFile : inputFiles) {
                merger.addSource(new File(inputFile));
            }

            // Set output merged PDF file
            merger.setDestinationFileName(outputFile);

            // Merge PDF files
            merger.mergeDocuments(null);

            System.out.println("PDF files merged successfully to: " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
