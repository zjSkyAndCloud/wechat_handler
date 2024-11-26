package com.github.skyandcloud.wechatclient.utils;

import com.github.skyandcloud.wechatclient.domain.message.FileContentEntity;
import com.github.skyandcloud.wechatclient.domain.message.UnescapeFileEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;


/**
 * @Description 微信文件工具类
 * @Author zheng.jiang
 * @Date 2024/11/22 16:10
 */
@Slf4j
public class WechatFileUtils {

    public static UnescapeFileEntity getUnescapeFile(String content) {
        if (ObjectUtils.isEmpty(content)) {
            log.error("content is empty");
            return null;
        }

        content = unescapeFileContent(content);
        FileContentEntity fileContentEntity = XmlUtils.xmlToJavaObject(content, FileContentEntity.class);
        if (ObjectUtils.isEmpty(fileContentEntity) || ObjectUtils.isEmpty(fileContentEntity.getAppMsg())) {
            log.error("unescape file fail");
            return null;
        }

        String title = null;
        if (ObjectUtils.isEmpty(fileContentEntity.getAppMsg().getTitle())) {
            log.error("file title is empty");
            return null;
        }

        title = fileContentEntity.getAppMsg().getTitle();

        Long length = null;
        if (ObjectUtils.isEmpty(fileContentEntity.getAppMsg().getAppattach()) || ObjectUtils.isEmpty(fileContentEntity.getAppMsg().getAppattach().getTotalLen())) {
            log.error("file length is empty");
            return null;
        }

        length = fileContentEntity.getAppMsg().getAppattach().getTotalLen();
        UnescapeFileEntity unescapeFileEntity = new UnescapeFileEntity();
        unescapeFileEntity.setFileName(title);
        unescapeFileEntity.setLength(length);
        return unescapeFileEntity;
    }

    /**
     * XML转义
     *
     * @param content
     * @return
     */
    public static String unescapeFileContent(String content) {
        content = content.replace("\\n", "").replace("\\t", "").replace("\\\"", "");
        content = content.replace("<?xml version=1.0?>", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        Integer startIndex = content.indexOf("<appmsg");
        Integer endIndex = content.indexOf("sdkver=\"0\">") + 9;
        content = content.substring(0, startIndex) + "<appmsg>" + content.substring(endIndex);
        return content;
    }

}
