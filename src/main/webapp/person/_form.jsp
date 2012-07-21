<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div style="color:red;">
	<s:fielderror />
	<s:actionmessage/>
	<s:actionerror/>
</div>
<div class="error"></div>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="person.model"/><s:text name="title.info"/></td>
        <s:hidden key="person.id"/>
        <s:token/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="person.firstName"/></td>
        <td class="page-form-value"><s:textfield key="person.firstName"/></td>
        <td/>
        <td/>
	</tr>
	<tr>
        <td class="page-form-label"><s:text name="person.lastName"/></td>
        <td class="page-form-value"><s:textfield key="person.lastName"/></td>
        <td/>
        <td/>
	</tr>
	<tr><td colspan="4"><div class="line"></div></td></tr>
	<tr>
        <td class="page-form-label"><s:text name="common.createdBy"/></td>
        <td class="page-form-value"><s:property value="person.createdBy"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.createdAt"/></td>
        <td class="page-form-value"><s:property value="person.createdAt"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.updatedBy"/></td>
        <td class="page-form-value"><s:property value="person.updatedBy"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.updatedAt"/></td>
        <td class="page-form-value"><s:property value="person.updatedAt"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td width="20%"/>
        <td width="30%"/>
        <td width="20%"/>
        <td width="30%"/>
    </tr>
</table> 


<script type="text/javascript">
$.validator.setDefaults({
	submitHandler: function(form) {form.submit();}
});
$(document).ready(function() {
	$("form").validate({
		errorLabelContainer: $("div.error"),
		rules: {
			"person.firstName": {
				required: true,
				maxlength: 20
			},
			"person.lastName": {
				required: true,
				maxlength: 20
			}
		},
		messages: {
			"person.firstName": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='person.firstName'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='person.firstName'/></s:param><s:param>20</s:param></s:text>"
			},
			"person.lastName": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='person.lastName'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='person.lastName'/></s:param><s:param>20</s:param></s:text>"
			}
		}
	});
});
</script>
