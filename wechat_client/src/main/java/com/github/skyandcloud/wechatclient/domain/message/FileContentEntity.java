package com.github.skyandcloud.wechatclient.domain.message;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Description 文件内容解析实体类
 * @Author zheng.jiang
 * @Date 2024/11/22 14:09
 */

@Data
@XmlRootElement(name = "msg")
@XmlAccessorType(XmlAccessType.FIELD)
public class FileContentEntity {


    @XmlElement(name = "appmsg")
    private AppMsg appMsg;

    @XmlElement(name = "fromusername")
    private String fromUserName;


    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AppMsg {

        @XmlElement(name = "appattach")
        private AppAttach appattach;

        @XmlElement(name = "title")
        private String title;

    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AppAttach {
        @XmlElement(name = "totallen")
        Long totalLen;
    }


}
