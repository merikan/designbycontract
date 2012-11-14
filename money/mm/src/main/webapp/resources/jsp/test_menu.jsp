<link rel="stylesheet" href="../../resources/css/test_menu_styles.css" type="text/css" />
<%@ include file="/resources/jsp/taglibs.jspf"%>

<div id="nav_menu" >
	<ul style="width: 100%;">
		<li class='active'><a href='/mm/pages/dashboard/view'><span>Dashboard</span></a></li>
		<li class='has-sub'><a href='#'><span>Chart</span></a>
			<ul>
				<li><a href='/mm/pages/chart/view'><span>Pie Chart</span></a></li>
				<li><a href='/mm/pages/chart/viewBarChart'><span>Bar Chart</span></a></li>
			</ul>
		</li>
		<li class='has-sub'><a href='#'><span>Transactions</span></a>
			<ul>
				<li><a href='/mm/pages/transaction/view'><span>All Spending</span></a></li>
				<li><a href='/mm/pages/transaction/viewAllCategories'><span>Spending By Category</span></a></li>
			</ul>
		</li>						
		<li class='active'><a href='/mm/pages/file/view'><span>Upload</span></a></li>
				<c:set var="user" value="${sessionScope.SESSION_STATE.loggedOnUser}" />
				<c:set var="account" value="${sessionScope.SESSION_STATE.account}" />
		<span id="loggedOnUser">
			
			<a href='/icon/pages/logout/exit'>Logout</a> 
			<a href="/icon/pages/feedback/create">Selected Account : ${account.name} &nbsp; |</a>
			<a href='/icon/pages/user/viewUser?id=${user.id}'>User : ${user.displayName} &nbsp;&nbsp; |</a>

	</span>
</ul>

</div>
