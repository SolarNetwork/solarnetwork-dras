<div class="panelTitle ui-state-active ui-corner-top">
Create User
</div>

<form id="newUserForm" method="POST" autocomplete="off">
	
	<table>
		<tr><td>Username: </td><td><input type="text" name="user.username" class="required"/></td></tr>
		<tr><td>Name: </td><td><input type="text" name="user.displayName" class="required" autocomplete="off"/></td></tr>
		<tr><td>Password: </td><td><input type="password" name="user.password" class="required" value="" autocomplete="new-password"/></td></tr>
		
		<%@ include file="userDetails.jspf" %>
		
		<tr><td></td><td><input type="submit" id="createUserButton" value="Create User" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"/></td></tr>
	</table>

</form>
