package com.jawwad.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

class CreateVehicleModelTemplateFile {

    public static void main(String[] args) throws Exception {

        Map<String, String[]> categoryItems = new HashMap<>();
        categoryItems.put("vehicleTypes", new String[]{"type 1", "type 2", "type 3"});
        categoryItems.put("vehicleBrand", new String[]{"Brand 1", "Brand 2", "Brand 3"});

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("vehicleMetaInformation");

        Row row;
        Name namedRange;
        String colLetter;
        String reference;

        int columnIndex = 0;
        for (String key : categoryItems.keySet()) {
            int rowIndex = 0;
            row = sheet.getRow(rowIndex);
            if (row == null) row = sheet.createRow(rowIndex);
            rowIndex++;
            row.createCell(columnIndex).setCellValue(key);
            String[] items = categoryItems.get(key);
            for (String item : items) {
                row = sheet.getRow(rowIndex);
                if (row == null) row = sheet.createRow(rowIndex);
                rowIndex++;
                row.createCell(columnIndex).setCellValue(item);
            }
            colLetter = CellReference.convertNumToColString(columnIndex);
            namedRange = workbook.createName();
            namedRange.setNameName(key);
            reference = "vehicleMetaInformation!$" + colLetter + "$2:$" + colLetter + "$" + rowIndex;
            namedRange.setRefersToFormula(reference);
            columnIndex++;
        }

        sheet.setSelected(false);
        sheet.protectSheet("password");

        sheet = workbook.createSheet("vehicle model information");

        sheet.createRow(0);
        sheet.getRow(0).createCell(0).setCellValue("Model name");
        sheet.getRow(0).createCell(1).setCellValue("Fuel tank size");
        sheet.getRow(0).createCell(2).setCellValue("Select vehicle type");
        sheet.getRow(0).createCell(3).setCellValue("Select vehicle brand");

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);

        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint("vehicleTypes");
        CellRangeAddressList addressList = new CellRangeAddressList(1, 1000, 2, 2);
        DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);
        validation.setShowErrorBox(true);
        validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
        validation.createErrorBox("Invalid data", "Please select data from the dropdown menu.");
        sheet.addValidationData(validation);

        dvConstraint = dvHelper.createFormulaListConstraint("vehicleBrand");
        addressList = new CellRangeAddressList(1, 1000, 3, 3);
        validation = dvHelper.createValidation(dvConstraint, addressList);
        validation.setShowErrorBox(true);
        validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
        validation.createErrorBox("Invalid data", "Please select data from the dropdown menu.");
        sheet.addValidationData(validation);

        dvConstraint = dvHelper
                .createDecimalConstraint(DataValidationConstraint.OperatorType.BETWEEN, "0", "999999999999999");
        addressList = new CellRangeAddressList(1, 1000, 1, 1);
        validation = dvHelper.createValidation(dvConstraint, addressList);
        validation.setShowErrorBox(true);
        validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
        validation.createErrorBox("Invalid data", "Please numeric number");

        sheet.addValidationData(validation);

        workbook.setSheetHidden(0, true);
        workbook.setActiveSheet(1);
        FileOutputStream out = new FileOutputStream("vehicle model upload template.xlsx");
        workbook.write(out);
        workbook.close();
        out.close();
    }
}
