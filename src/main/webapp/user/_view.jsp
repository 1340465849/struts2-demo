<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="user.model"/><s:text name="title.info"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="user.firstName"/></td>
        <td class="page-form-value"><span id="show_user_firstName"><s:property value="user.firstName"/></span></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="user.lastName"/></td>
        <td class="page-form-value"><span id="show_user_lastName"><s:property value="user.lastName"/></span></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td width="20%"></td>
        <td width="30%"></td>
        <td width="20%"></td>
        <td width="30%"></td>
    </tr>
</table> 