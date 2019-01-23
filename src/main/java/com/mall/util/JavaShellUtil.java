package com.mall.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaShellUtil {

    private JavaShellUtil() {
    }

    public static int executeShell(String shellCommand) throws IOException {
        int success = 0;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            log.info("execute shell command : {}", shellCommand);
            Process pid = null;
            String[] cmd = {"/bin/sh", "-c", shellCommand};
            //执行Shell命令
            pid = Runtime.getRuntime().exec(cmd);
            if (pid != null) {
                log.info("pid : {}", pid.toString());
                //pid.waitFor();
                //bufferedReader用于读取Shell的输出内容
                bufferedInputStream = new BufferedInputStream(pid.getInputStream());
                bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
                pid.waitFor();
            } else {
                log.info("none pid ");
            }
            log.info("Shell end \r\n result： ");
            String line = null;
            //读取Shell的输出内容，并添加到stringBuffer中
            while (Objects.nonNull(bufferedReader) && (line = bufferedReader.readLine()) != null) {
                log.info("\r\n" + line);
            }
        } catch (Exception ioe) {
            stringBuilder.append("execute error ：\r\n").append(ioe.getMessage()).append("\r\n");
            log.error("execute error : {} ", ioe.getMessage());
            try {
                if (Objects.nonNull(bufferedReader)) {
                    bufferedReader.close();
                }
                if (Objects.nonNull(bufferedInputStream)) {
                    bufferedInputStream.close();
                }
            } catch (Exception e) {
                log.info("关闭流异常");
            }
            success = 1;
        }
        return success;
    }

    public static void main(String[] args) throws IOException {
        executeShell("ps -a");
        //executeShell("mysqldump -h localhost -uroot -p'890p;/.,ki' -database mmall -r /tmp/back.sql");
    }
}
