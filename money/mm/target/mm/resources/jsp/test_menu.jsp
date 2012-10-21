<link rel="stylesheet" href="../../resources/css/test_menu_styles.css" type="text/css" />

<div id="nav">
	<ul style="width: 1250px">
		<li class='active'><a href='/icon/pages/dashboard/viewAll'><span>Dashboard</span></a></li>
		<li class='has-sub'><a href='#'><span>Create</span></a>
			<ul>
				<li><a href='/icon/pages/triage/new'><span>New Change</span></a></li>
			</ul>
		</li>
		<span id="loggedOnUser">
			<c:set var="user" value="${sessionScope.SESSION_STATE.loggedOnUser}" />
			<a href='/icon/pages/logout/exit'>Logout</a> 
			<a href="/icon/pages/feedback/create">Feedback  &nbsp;&nbsp; |</a>
			<a href='/icon/pages/user/viewUser?id=${user.id}'>User : ${user.displayName} &nbsp;&nbsp; |</a>

	</span>
</ul>

</div>
