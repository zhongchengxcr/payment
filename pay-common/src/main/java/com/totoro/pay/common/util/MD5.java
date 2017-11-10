package com.totoro.pay.common.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;

/**
 * ���ܣ�MD5ǩ��
 * �汾��3.3
 * �޸����ڣ�2012-08-17
 */
public class MD5 {
    public static void main(String[] args) {
        //String str = "appid=wx8632a91376b81e24&charset=UTF-8&code_img_url=https://paya.swiftpass.cn/pay/qrcode?uuid=weixin://wxpay/bizpayurl?pr=GLaoreT&code_url=weixin://wxpay/bizpayurl?pr=GLaoreT&mch_id=4102900001&nonce_str=fWGUkZVuLKQf5vpC&result_code=0&sign_type=MD5&status=0&uuid=1df519716337159f8e2f7e2c26742d37&version=2.0";
        //String str = "appid=wx8632a91376b81e24&charset=UTF-8&mch_id=4102900001&nonce_str=mhIAEw3R6DnNRL3v&result_code=0&sign_type=MD5&status=0&token_id=4e915a6fe3d36a7cc6bb5c6a7110b5ee&version=2.0";
        String str = "appid=wx8632a91376b81e24&charset=UTF-8&mch_id=4102900001&nonce_str=JifGK5lqRLaVZ2qw&result_code=0&sign_type=MD5&status=0&token_id=be8dc03af91ecc880e584d027ae3d515&version=2.0";
        System.out.println(MD5.sign(str, "&key=659ff1e5061ada577869b791", "utf-8"));

    }

    /**
     * ǩ���ַ���
     *
     * @param text          ��Ҫǩ�����ַ���
     * @param key           ��Կ
     * @param input_charset �����ʽ
     * @return ǩ�����
     */
    public static String sign(String text, String key, String input_charset) {
        text = text + key;
        System.out.println(text);
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }

    /**
     * ǩ���ַ���
     *
     * @param text          ��Ҫǩ�����ַ���
     * @param sign          ǩ�����
     * @param key           ��Կ
     * @param input_charset �����ʽ
     * @return ǩ�����
     */
    public static boolean verify(String text, String sign, String key, String input_charset) {
        text = text + key;
        String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
        if (mysign.equals(sign)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5ǩ�������г��ִ���,ָ���ı��뼯����,��Ŀǰָ���ı��뼯��:" + charset);
        }
    }

}