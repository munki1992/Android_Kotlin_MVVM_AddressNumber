package com.munki.android_kotlin_mvvm_address_number.util.permission

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*

/**
 * 권한 요청 util
 * @author 나비이쁜이
 * @since 2020.10.06
 */
object PermissionUtil {

    /**
     * 요청이 필요한 권한 목록을 getRequiredPermissions 메서드를 통해 구한 후 사용자에게 실제 권한 요청을 수행한다.
     */
    @JvmStatic
    fun checkAndRequestPermission(activity: Activity, permissionCode: Int, vararg permissions: String): Boolean {
        val requiredPermissions = getRequiredPermissions(activity, *permissions)

        // 6.0 이하 버전에서는 이 결과가 무조건 '허용'으로 처리되기 때문에
        // Activity의 requestPermissions에서 무조건 '허용' 처리된다.
        return if (requiredPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(activity, requiredPermissions, permissionCode)
            false
        } else true
    }

    /**
     * 요청이 필요한 권한 목록 가져오기
     */
    @JvmStatic
    private fun getRequiredPermissions(context: Context?, vararg permissions: String): Array<String> {
        val requirePermissionList: MutableList<String> = ArrayList()

        // Context가 null이면 무조건 권한을 요청하도록 requiredPermissions가 존재한다고 reutrn 한다
        if (context == null) return requirePermissionList.toTypedArray()

        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                requirePermissionList.add(permission)
        }
        return requirePermissionList.toTypedArray()
    }

    /**
     * 사용자가 권한 허용/거부 했는지 체크하는 메서드
     */
    @JvmStatic
    fun verifyPermissions(grantResults: IntArray): Boolean {
        // grantResults가 존재하지 않으면 '거부' 처리된 것으로 함.
        if (grantResults.isEmpty()) return false

        for (result in grantResults) {
            // 요청 권한 중에 하나라도 '거부'했다면 전체를 '거부' 처리한 것으로 함.
            if (result != PackageManager.PERMISSION_GRANTED) return false
        }
        return true
    }
}