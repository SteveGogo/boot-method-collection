package com.learn.springsecuritymyimpl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 路径权限关联表
 * @TableName sys_request_path_permission_relation
 */
@TableName(value ="sys_request_path_permission_relation")
@Data
public class SysRequestPathPermissionRelation implements Serializable {
    /**
     * 主键id
     */
    @TableField(value = "id")
    private Integer id;

    /**
     * 请求路径id
     */
    @TableField(value = "url_id")
    private Integer urlId;

    /**
     * 权限id
     */
    @TableField(value = "permission_id")
    private Integer permissionId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysRequestPathPermissionRelation other = (SysRequestPathPermissionRelation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUrlId() == null ? other.getUrlId() == null : this.getUrlId().equals(other.getUrlId()))
            && (this.getPermissionId() == null ? other.getPermissionId() == null : this.getPermissionId().equals(other.getPermissionId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUrlId() == null) ? 0 : getUrlId().hashCode());
        result = prime * result + ((getPermissionId() == null) ? 0 : getPermissionId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", urlId=").append(urlId);
        sb.append(", permissionId=").append(permissionId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}