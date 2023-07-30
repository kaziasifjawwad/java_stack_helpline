package com.jawwad.excel;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VehicleBulkUpload {
    private static final String sheetOfVehicleModelAndBrandInfo = "vehicleModelAndBrand";
    private static final String sheetOfManufacturer = "manufactureYear";
    private static final String brandConstrain = "brandConstrain";
    private static final String yearConstrain = "yearConstrain";
    private static final String driverConstrain = "driverConstrain";
    private static final String sheetOfDriver = "sheetOfDriver";
    private static final int driverCodeColumnNumber = 10;
    private static final int nextInspectionDateColNumber = 9;
    private static final int lastInspectionDateColNumber = 8;
    private static final int insuranceExpirationColNum = 7;
    private static final int insuranceIssueDateColNumber = 6;
    private static final int manufactureYearColNum = 5;
    private static final int engineNumberColNum = 4;
    private static final int modelNameColNumb = 3;
    private static final int brandNameColNum = 2;
    private static final int plateNumberColNum = 1;
    private static final int seriesNumberColNum = 0;
    private static final int lastRowOfTheSheet = SpreadsheetVersion.EXCEL2007.getLastRowIndex();
    private static Workbook workbook;
    private static String colLetter;

    public static void main(String[] args) throws Exception {
        workbook = new XSSFWorkbook();
        //some data
        Map<String, String[]> categoryItems = new HashMap<>();
        categoryItems.put("Coun tries", new String[]{});
        categoryItems.put("Capi tals", new String[]{});
        categoryItems.put("University", new String[]{"BRAC","NSU"});
        createVehicleMetaData(categoryItems,workbook);
        createManufacturerSheet();
        createDriverInfoSheet(Set.of("Asif", "Arif"));
        Sheet sheet = workbook.createSheet("Sheet1");
        createHeader(sheet);
        sheet.setActiveCell(new CellAddress("C2"));

        createValidationForVehicleModel(sheet);
        createSeriesNumberValidation(sheet);
        createPlateNumberValidation(sheet);
        createValidationForManufacturerYear(sheet);
        createDateValidation(sheet);
        createDriverValidation(sheet);
        workbook.setActiveSheet(1);
        FileOutputStream out = new FileOutputStream("vehicleTemplate.xlsx");
        workbook.write(out);
        workbook.close();
        out.close();
    }

    private static void createDriverValidation(Sheet sheet) {
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint(driverConstrain);
        setColumnValidation(
                sheet,
                dvHelper,
                dvConstraint,
                driverCodeColumnNumber,
                "Please select data from the dropdown menu.");
    }

    private static void createDateValidation(Sheet sheet){
        DataFormat fmt = workbook.createDataFormat();
        CellStyle textStyle = workbook.createCellStyle();
        textStyle.setDataFormat(fmt.getFormat("@"));
        sheet.setDefaultColumnStyle(insuranceIssueDateColNumber, textStyle);
        sheet.setDefaultColumnStyle(insuranceExpirationColNum, textStyle);
        sheet.setDefaultColumnStyle(lastInspectionDateColNumber, textStyle);
        sheet.setDefaultColumnStyle(nextInspectionDateColNumber, textStyle);
    }

    private static void createValidationForManufacturerYear(Sheet sheet) {
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint(yearConstrain);
        setColumnValidation(
                sheet,
                dvHelper,
                dvConstraint,
                manufactureYearColNum,
                "Please select data from the dropdown menu.");
    }

    private static void createPlateNumberValidation(Sheet sheet){
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint =
                dvHelper.createNumericConstraint(DataValidationConstraint.ValidationType.TEXT_LENGTH,
                        DataValidationConstraint.OperatorType.BETWEEN, "4", "4");
        setColumnValidation(sheet,dvHelper,dvConstraint,1,"Plate number length should be 4");
    }

    private static void createSeriesNumberValidation(Sheet sheet){
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint =
                dvHelper.createNumericConstraint(DataValidationConstraint.ValidationType.TEXT_LENGTH,
                        DataValidationConstraint.OperatorType.BETWEEN, "3", "3");
        setColumnValidation(sheet,dvHelper,dvConstraint,0,"Series number length should be 3");
    }

    private static void setColumnValidation(
            Sheet sheet,
            DataValidationHelper dvHelper,
            DataValidationConstraint dvConstraint,
            int columnNumber,
            String errorMessage) {
        CellRangeAddressList addressList =
                new CellRangeAddressList(1, lastRowOfTheSheet, columnNumber, columnNumber);
        DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);
        validation.setShowErrorBox(true);
        validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
        validation.createErrorBox("Invalid data", errorMessage);
        sheet.addValidationData(validation);
    }

    private static void createHeader(Sheet sheet){
        sheet.createRow(0);
        Row headerRow = sheet.getRow(0);

        Cell seriesNumberCell= headerRow.createCell(seriesNumberColNum);
        seriesNumberCell.setCellValue("Series Number");
        sheet.autoSizeColumn(seriesNumberColNum);
        seriesNumberCell.setCellStyle(getCellStyle());

        Cell plateNumberCell= headerRow.createCell(plateNumberColNum);
        plateNumberCell.setCellValue("Plate Number");
        sheet.autoSizeColumn(plateNumberColNum);
        plateNumberCell.setCellStyle(getCellStyle());

        Cell brandNameCell= headerRow.createCell(brandNameColNum);
        brandNameCell.setCellValue("Brand name");
        sheet.autoSizeColumn(brandNameColNum);
        brandNameCell.setCellStyle(getCellStyle());

        Cell modelNameCell= headerRow.createCell(modelNameColNumb);
        modelNameCell.setCellValue("Model name");
        sheet.autoSizeColumn(modelNameColNumb);
        modelNameCell.setCellStyle(getCellStyle());

        Cell engineNumberCell= headerRow.createCell(engineNumberColNum);
        engineNumberCell.setCellValue("Engine Number");
        sheet.autoSizeColumn(engineNumberColNum);
        engineNumberCell.setCellStyle(getCellStyle());

        Cell manufactureYearCell= headerRow.createCell(manufactureYearColNum);
        manufactureYearCell.setCellValue("Manufacture Year");
        sheet.autoSizeColumn(manufactureYearColNum);
        manufactureYearCell.setCellStyle(getCellStyle());

        Cell insuranceIssueDateCell= headerRow.createCell(insuranceIssueDateColNumber);
        insuranceIssueDateCell.setCellValue("Insurance Issue Date\n(DD/MM/YYYY)");
        sheet.autoSizeColumn(insuranceIssueDateColNumber);
        insuranceIssueDateCell.setCellStyle(getCellStyle());

        Cell insuranceExpirationCell= headerRow.createCell(insuranceExpirationColNum);
        insuranceExpirationCell.setCellValue("Insurance Expiration\nDate (DD/MM/YYYY)");
        sheet.autoSizeColumn(insuranceExpirationColNum);
        insuranceExpirationCell.setCellStyle(getCellStyle());

        Cell lastInspectionDateCell= headerRow.createCell(lastInspectionDateColNumber);
        lastInspectionDateCell.setCellValue("Last Inspection Date\n(DD/MM/YYYY)");
        sheet.autoSizeColumn(lastInspectionDateColNumber);
        lastInspectionDateCell.setCellStyle(getCellStyle());

        Cell inspectionDateCell= headerRow.createCell(nextInspectionDateColNumber);
        inspectionDateCell.setCellValue("Next Inspection Date\n(DD/MM/YYYY)");
        sheet.autoSizeColumn(9);
        inspectionDateCell.setCellStyle(getCellStyle());

        Cell driverCodeCell= headerRow.createCell(driverCodeColumnNumber);
        driverCodeCell.setCellValue("Driver Code");
        sheet.autoSizeColumn(driverCodeColumnNumber);
        driverCodeCell.setCellStyle(getCellStyle());

        headerRow.setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));
    }
    private static void createVehicleMetaData(Map<String, String[]> categoryItems, Workbook workbook){
        Sheet sheet = workbook.createSheet(sheetOfVehicleModelAndBrandInfo);

        Row row;
        Name namedRange;
        String reference;

        int c = 0;
        for (String key : categoryItems.keySet()) {
            int r = 0;
            row = sheet.getRow(r); if (row == null) row = sheet.createRow(r); r++;
            row.createCell(c).setCellValue(key);
            String[] items = categoryItems.get(key);
            for (String item : items) {
                row = sheet.getRow(r); if (row == null) row = sheet.createRow(r); r++;
                row.createCell(c).setCellValue(item);
            }
            c++;
        }
        colLetter = CellReference.convertNumToColString((c-1));
        namedRange = workbook.createName();
        namedRange.setNameName(brandConstrain);
        reference = sheetOfVehicleModelAndBrandInfo +"!$A$1:$" + colLetter + "$1";
        namedRange.setRefersToFormula(reference);
        sheet.setSelected(false);
    }

    private static void createManufacturerSheet(){
        Sheet sheet = workbook.createSheet(sheetOfManufacturer);
        Row row;
        Name namedRange;
        String colLetter;
        String reference;
        int c = 0;
        int rowIndex = 0;
            row = sheet.getRow(rowIndex); if (row == null) row = sheet.createRow(rowIndex); rowIndex++;
            row.createCell(c).setCellValue("Manufacture year");
            int currentYear = OffsetDateTime.now().getYear();
            for ( int year = currentYear; year>=1980;year--) {
                row = sheet.getRow(rowIndex); if (row == null) row = sheet.createRow(rowIndex); rowIndex++;
                row.createCell(c).setCellValue(year);
            }

        colLetter = CellReference.convertNumToColString(0);
        namedRange = workbook.createName();
        namedRange.setNameName(yearConstrain);
        reference = sheetOfManufacturer +"!$" + colLetter + "$2:$" + colLetter + "$" + rowIndex;
        namedRange.setRefersToFormula(reference);
        sheet.setSelected(false);
    }

    private static void createDriverInfoSheet(Collection<String> driverCode){
        Sheet sheet = workbook.createSheet(sheetOfDriver);
        Row row;
        Name namedRange;
        String colLetter;
        String reference;
        int c = 0;
        int rowIndex = 0;
        row = sheet.getRow(rowIndex); if (row == null) row = sheet.createRow(rowIndex); rowIndex++;
        row.createCell(c).setCellValue("Driver code");
        for (String year: driverCode) {
            row = sheet.getRow(rowIndex); if (row == null) row = sheet.createRow(rowIndex); rowIndex++;
            row.createCell(c).setCellValue(year);
        }

        colLetter = CellReference.convertNumToColString(0);
        namedRange = workbook.createName();
        namedRange.setNameName(driverConstrain);
        reference = sheetOfDriver +"!$" + colLetter + "$2:$" + colLetter + "$" + rowIndex;
        namedRange.setRefersToFormula(reference);
        sheet.setSelected(false);
    }

    private static void createValidationForVehicleModel(Sheet sheet){
        //data validations
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        //data validation for categories in A2:
//        DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint(brandConstrain);
        DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint(brandConstrain);
        CellRangeAddressList addressList = new CellRangeAddressList(1, lastRowOfTheSheet, 2, 2);
        DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);
        sheet.addValidationData(validation);

//        dvConstraint = dvHelper
//                .createFormulaListConstraint(
//                        "=OFFSET(vehicleModelAndBrand!$A$1, 1, MATCH($C2, vehicleModelAndBrand!$A$1:$C$1, 0) - 1, COUNTA(OFFSET(vehicleModelAndBrand!$A$1, 1, MATCH($C2, vehicleModelAndBrand!$A$1:$C$1, 0) - 1, 15)), 1)");


        dvConstraint = dvHelper
                .createFormulaListConstraint(generateDependencyConstrain());

        addressList = new CellRangeAddressList(1, lastRowOfTheSheet, 3, 3);
        validation = dvHelper.createValidation(dvConstraint, addressList);
        sheet.addValidationData(validation);
    }

    private static String generateDependencyConstrain(){
        var expression = "=OFFSET("+
                sheetOfVehicleModelAndBrandInfo
    +"!$A$1"
                +
                ", 1, MATCH($"+
                "C2"+
                ", "+
                sheetOfVehicleModelAndBrandInfo+
    "!$A$1:$"+
        colLetter+"$1"+
            ", 0) - 1, COUNTA(OFFSET(" +
                sheetOfVehicleModelAndBrandInfo+
            "!$A$1"+
            ", 1, MATCH($"+
            "C2"+
            ", "+
                sheetOfVehicleModelAndBrandInfo +
    "!$A$1"+
    ":$"+
            colLetter+"$1, 0) - 1, "+
            15+
    ")), 1)";
        return expression;
    }

    private static  CellStyle getCellStyle() {
        CellStyle cellStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerFont.setBold(true);
        cellStyle.setFont(headerFont);
        cellStyle.setWrapText(true);
        return cellStyle;
    }

    private static CellStyle getCellStyleForDate(){
        CellStyle dateCellStyle = getCellStyle();
        XSSFDataFormat dateDataFormat = (XSSFDataFormat)workbook.createDataFormat();
        dateCellStyle.setDataFormat(dateDataFormat.getFormat(("dd-mm-yyyy")));
        return dateCellStyle;
    }
}
