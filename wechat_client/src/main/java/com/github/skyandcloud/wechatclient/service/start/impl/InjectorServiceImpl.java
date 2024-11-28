package com.github.skyandcloud.wechatclient.service.start.impl;

import com.github.skyandcloud.wechatclient.service.start.RunService;
import com.github.skyandcloud.wechatclient.service.wechat.impl.WechatPackingApiServiceImpl;
import com.github.skyandcloud.wechatclient.service.wechat.WechatPackingApiService;
import com.github.skyandcloud.wechatclient.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.io.*;

/**
 * @Description dll注入服务
 * @Author zheng.jiang
 * @Date 2024/11/24 16:13
 */
@Slf4j
public class InjectorServiceImpl implements RunService {

    private static final WechatPackingApiService WECHAT_PACKING_API_SERVER = new WechatPackingApiServiceImpl();

    /**
     * 注入程序
     */
    private static final String INJECTOR_FILE_NAME = "injector";

    /**
     * 注入的dll文件
     */
    private static final String DLL_FILE_NAME = "wxhelper";

    /**
     * 注入程序
     *
     * @throws Exception
     */
    public void run() throws Exception {
        log.info("inject start");
        File consoleInjectFile = null;
        File dllFile = null;
        try {
            consoleInjectFile = copyInjectorFile();
            dllFile = copyDllFile();
            String command = getCommand(consoleInjectFile, dllFile);
            log.info("inject command: {}", command);
            tryInjector(command);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            FileUtils.delete(consoleInjectFile);
            FileUtils.delete((dllFile));
            log.info("inject success");
        }
    }

    /**
     * 复制注入文件
     *
     * @return 注入文件
     * @throws IOException
     */
    private static File copyInjectorFile() throws IOException {
        return FileUtils.createTempFile(INJECTOR_FILE_NAME, ".exe");
    }

    /**
     * 复制dll文件
     *
     * @return dll文件
     * @throws IOException
     */
    private static File copyDllFile() throws IOException {
        return FileUtils.createTempFile(DLL_FILE_NAME, ".dll");
    }


    /**
     * 根据命令尝试注入
     *
     * @param command
     * @return
     */
    private static void tryInjector(String command) throws Exception {
        executeShell(command);
        try {
            //检查登录状态
            Boolean isLogin = WECHAT_PACKING_API_SERVER.isLogin();
            if (isLogin) {
                log.info("injector success");
            }

        } catch (Exception e) {
            log.error("injector failed", e);
            throw e;
        }

    }

    /**
     * 根据临时文件创建命令
     *
     * @param injectFile 注入工具
     * @param dllFile    dll文件
     * @return
     */
    private static String getCommand(File injectFile, File dllFile) {
        String consoleInjectAbsolutePath = injectFile.getAbsolutePath();
        String dllAbsolutePath = dllFile.getAbsolutePath();
        return consoleInjectAbsolutePath + " --process-name WeChat.exe --inject " + dllAbsolutePath;
    }

    /**
     * 执行shell 命令
     *
     * @param command
     */
    public static void executeShell(String command) {
        if (ObjectUtils.isEmpty(command)) {
            log.error("command is null");
            return;
        }

        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                log.info(line);
            }

            int exitCode = process.waitFor();
            log.info("Exit Code: {}", exitCode);
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

}
