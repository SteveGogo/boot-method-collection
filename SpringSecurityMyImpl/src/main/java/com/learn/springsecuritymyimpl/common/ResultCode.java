package com.learn.springsecuritymyimpl.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description: 响应状态及信息枚举
 */
@Getter
public enum ResultCode {
    UNKNOWN_ERROR(-1, "未知错误"),
    SUCCESS(200, "请求成功"),
    DATA_EXIST(201, "数据已存在"),
    API_DATA_ERROR(203, "请求参数异常"),
    NAME_EXIST(204, "名称不能重复"),
    NAME_NUMBER_EXIST(205, "名称或代码已经存在"),
    IMG_COUNT_ERROR(206, "上传图片超过配置数量"),
    DATA_IS_NULL(300, "数据为空"),
    AUTHENTICATION_FAIL(401, "登录认证失败"),
    TOKEN_EXPIRED(402, "登录超时"),
    UNAUTHORIZED(403, "权限不足"),
    USERNAME_PASSWORD_ERROR(405, "账号密码错误"),
    SSO_ERROR(444, "该账号在其他地方登录,请重新登录!"),
    LICENSE_ERROR(445, "软件授权码错误!"),
    SERVER_ERROR(500, "服务器错误"),

    //--------------------系统相关-----------------------
    success(200, "操作成功"),
    pay_success(201, "支付成功"),
    unauthorized(401, "Unauthorized"),
    not_login(403, "not_login"),
    not_found(404, "not_found"),
    no_permission(405, "无权限"),
    equipment_error(406, "设备类型错误"),
    platform_error(407, "未知的平台类型"),
    error(500, "服务器繁忙"),
    repeated_operate(600, "重复操作"),

    //--------------------业务相关-----------------------
    error_params(10000, "参数错误"),
    frequent_get_verify_code(10001, "频繁获取验证码"),
    error_verify_code(10002, "验证码错误"),
    error_verify(10003, "用户不存在或密码错误"),
    error_wechat_login(10004, "微信登录失败"),
    not_bind_phone(10005, "未绑定手机号"),
    not_tags_count(10006, "请选择1至4个标签"),
    no_goods(10007, "商品不存在"),
    not_on_sale(10008, "商品已下架"),
    free_goods(10009, "免费商品"),
    coupon_not_exist(10010, "优惠券不存在"),
    coupon_used(10011, "优惠券已使用"),
    coupon_timeout(10012, "优惠券已过期"),
    coupon_not_match(10013, "不满足使用优惠券条件"),
    pay_fail(10014, "支付失败"),
    bought(10015, "重复购买"),
    subscribe(10016, "重复预约"),
    subscribe_error(10017, "预约失败，直播正在进行或已结束"),
    not_buy(10018, "未购买该商品"),
    not_live(10019, "未直播"),
    applied(10020, "已申请，请勿重复提交"),
    vip_user(10021, "会员用户"),
    not_subscribe_gzh(10022, "未关注公众号"),
    exceed_download_count_limit(10023, "超过下载次数限制"),
    focus_on_self(10024, "不能关注自己"),
    collected(10025, "重复收藏"),
    phone_bind(10026, "手机号已被绑定"),
    account_bind(10027, "三方号已被绑定"),
    rec_not_found(10028, "尚未生成回放视频"),
    cdkey_redeemed(10029, "已兑换"),
    error_share_code_times(10030, "频繁使用分享码"),
    share_code_used(10031, "分享码已被他人领取"),
    user_exist(10032, "用户已存在"),
    user_invited(10033, "已被邀请"),
    timout(10034, "操作过期"),
    type_error(10035,"类型错误"),


    enterprise_overdue(20000, "企业不存在或已过期"),
    enterprise_disabled_user(20001, "企业账号被禁用"),
    enterprise_admin_user(20002, "企业管理员"),
    enterprise_main_user(20003, "企业主账号"),
    enterprise_sub_user(20004, "企业子账号"),
    enterprise_user_exist(20005, "用户已在企业中"),
    enterprise_user_not_exist(20006, "用户不在企业中"),
    admin_user_num_limit(20007, "管理员人数超过限制"),
    admin_user_count_limit(20008, "设置管理员次数超过限制"),
    enterprise_account_limit(20009, "账号数量超过限制"),
    no_capacity(20010, "存储容量不足"),
    not_join_enterprise(20011, "尚未加入企业"),

    //-------------------------------------
    write_off_success(21001,"券码核销成功"),
    write_off_fail(21002,"券码核销失败"),
    write_off_time_error(21003,"不是券码使用时间"),


    /* 成功 */
    SUCCESS_login(200, "用户登录成功"),
    SUCCESS_logout(200, "用户退出成功"),

    /* 默认失败 */
    COMMON_FAIL(999, "失败"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),

    /* 业务错误 */
    NO_PERMISSION(3001, "当前账号没有此权限")


    ;



    private Integer code;
    private String msg;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    /**
     * 根据code获取message
     *
     * @param code
     * @return
     */
    public static String getMessageByCode(Integer code) {
        for (ResultCode ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getMessage();
            }
        }
        return null;
    }



}
