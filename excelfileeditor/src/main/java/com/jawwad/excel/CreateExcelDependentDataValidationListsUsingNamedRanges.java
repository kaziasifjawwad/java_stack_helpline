package com.jawwad.excel;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.util.*;

import java.util.Map;
import java.util.HashMap;

class CreateExcelDependentDataValidationListsUsingNamedRanges {

    public static void main(String[] args) throws Exception {

        //some data
        Map<String, String[]> categoryItems = new HashMap<>();
        categoryItems.put("Countries", new String[]{"France", "Germany", "Italy"});
        categoryItems.put("Capitals", new String[]{"Paris", "Berlin", "Rome"});
        categoryItems.put("Fruits", new String[]{"Apple", "Peach", "Banana", "Orange"});

        Workbook workbook = new XSSFWorkbook();

        //hidden sheet for list values
        Sheet sheet = workbook.createSheet("ListSheet");

        Row row;
        Name namedRange;
        String colLetter;
        String reference;

        int c = 0;
        //put the data in
        for (String key : categoryItems.keySet()) {
            int r = 0;
            row = sheet.getRow(r); if (row == null) row = sheet.createRow(r); r++;
            row.createCell(c).setCellValue(key);
            String[] items = categoryItems.get(key);
            for (String item : items) {
                row = sheet.getRow(r); if (row == null) row = sheet.createRow(r); r++;
                row.createCell(c).setCellValue(item);
            }
            //create names for the item list constraints, each named from the current key
            colLetter = CellReference.convertNumToColString(c);
            namedRange = workbook.createName();
            namedRange.setNameName(key);
            reference = "ListSheet!$" + colLetter + "$2:$" + colLetter + "$" + r;
            namedRange.setRefersToFormula(reference);
            c++;
        }

        //create name for Categories list constraint
        colLetter = CellReference.convertNumToColString((c-1));
        namedRange = workbook.createName();
        namedRange.setNameName("Categories");
        reference = "ListSheet!$A$1:$" + colLetter + "$1";
        namedRange.setRefersToFormula(reference);

        //unselect that sheet because we will hide it later
        sheet.setSelected(false);


        //visible data sheet
        sheet = workbook.createSheet("Sheet1");

        sheet.createRow(0).createCell(0).setCellValue("Select Category");
        sheet.getRow(0).createCell(1).setCellValue("Select item from that category");

        sheet.setActiveCell(new CellAddress("A2"));

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);

        //data validations
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        //data validation for categories in A2:
        DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint("Categories");
        CellRangeAddressList addressList = new CellRangeAddressList(1, 1000, 0, 0);
        DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);
        sheet.addValidationData(validation);

        //data validation for items of the selected category in B2:

        dvConstraint = dvHelper.createFormulaListConstraint("INDIRECT($A2)");
        addressList = new CellRangeAddressList(1, 1000, 1, 1);
        validation = dvHelper.createValidation(dvConstraint, addressList);
        sheet.addValidationData(validation);

        //hide the ListSheet
//        workbook.setSheetHidden(0, true);
        //set Sheet1 active
        workbook.setActiveSheet(1);

        FileOutputStream out = new FileOutputStream("CreateExcelDependentDataValidationListsUsingNamedRanges.xlsx");
        workbook.write(out);
        workbook.close();
        out.close();

    }
}
