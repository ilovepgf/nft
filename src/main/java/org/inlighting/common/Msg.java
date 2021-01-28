package org.inlighting.common;


public class Msg {
	
	private boolean success;

    // http 状态码
    private int code=200;

    // 返回信息
    private String msg;

    // 返回的数据
    private Object data;


    public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    public static Msg returnObj(boolean flag, String successMsg, String failMsg, Object data) {
        Msg msg = new Msg();
        msg.setSuccess(flag);
        msg.setData(data);
        if (flag) {
            msg.setMsg(successMsg);
        } else {
            msg.setMsg(failMsg);
            msg.setCode(500);
        }
        return msg;
    }
}
