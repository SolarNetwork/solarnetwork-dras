<div class="panelTitle ui-state-active ui-corner-top">
	Edit Program
</div>

<form id="editProgramForm" method="POST">
	<input type="hidden" name="program.id"/>
	
	<table>
		<tr><td>Created: </td><td class="programCreated"></td></tr>
		<tr><td>Name: </td><td><input type="text" name="program.name" class="required"/></td></tr>
		
		<tr><td></td><td><input id="saveProgram" type="submit" value="Save" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"></td></tr>
	</table>
		
	<div class="subPanelHeader ui-widget-header"><h4 class="title">Participants</h4><button id="newParticipantButton" type="button" class="subTitleButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">New Participant</button><div class="clear"></div></div>
	
	<div class="participantList">
		<table id="participantTable" class="display eventTable">
			<tbody></tbody>
		</table>
	</div>
		
	<div class="ui-widget-header subPanelHeader"><h4 class="title">Groups</h4><button id="newParticipantGroupButton" type="button" class="subTitleButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">New Group</button><div class="clear"></div></div>
		
	<div class="participantList">
		<table id="participantGroupsTable" class="display eventTable">
			<tbody></tbody>
		</table>
	</div>
	
</form>

