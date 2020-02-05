package com.junlc.admin.core.utils;

import com.junlc.admin.core.vo.ResultVo;

public class ResultVoUtil {

    /**
     * 操作有误
     * @param code 错误码
     * @param msg 提示信息
     */
    public static ResultVo error(Integer code, String msg){
        ResultVo resultVo = new ResultVo();
        resultVo.setMsg(msg);
        resultVo.setCode(code);
        return resultVo;
    }


}
