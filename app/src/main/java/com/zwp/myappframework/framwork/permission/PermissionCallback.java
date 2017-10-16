package com.zwp.myappframework.framwork.permission;

import java.util.List;

/**
 * Created by zwp on 2017/5/24.
 * 用于权限请求回调的集中和分发处理接口
 */

public interface PermissionCallback {
    void getPermissionSucceed(int requestCode, List<String> grantedPermissions);
    void getPermissionFail(int requestCode, List<String> deniedPermissions);
}
