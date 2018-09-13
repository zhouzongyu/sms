package com.gosuncn.ics.sms.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static Boolean writeInFile(String content, String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()){
                    logger.error(filePath + " 文件创建失败");
                    return false;
                }
            }catch (Exception e) {
                logger.error(filePath + " 文件创建失败: " + e);
                return false;
            }
        }


        try {
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.close();
            fileWriter.close();
        }catch (Exception e) {
            logger.error("文件写入失败: " +e );
            return false;
        }
        return true;
    }

    public String readFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            logger.error("文件不存在: " + filePath );
            return null;
        }


        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String tmpStr = null;
            while (null != (tmpStr = bufferedReader.readLine())){
                stringBuilder.append(tmpStr);
            }
            bufferedReader.close();
            return stringBuilder.toString();
        }catch (Exception e) {
            logger.error(filePath + " 文件读取失败: " + e);
        }

        return null;
    }

}
