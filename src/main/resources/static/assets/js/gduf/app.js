//整个前端应用中，只暴露gdufApp一个全局变量，若要定义全局变量，需统一管理，例如:gdufApp.config.router
var gdufApp = angular.module('gdufApp', ['ngRoute','ui.bootstrap']);

//路由信息
gdufApp.config.router = [
    {uri : "/calculate", templateUrl : "pages/calculate.html", controller : "calculateController"}
]

//配置信息
gdufApp.config(['$locationProvider', function($locationProvider) {
    $locationProvider.html5Mode(false);
}]);

//路由配置
gdufApp.config(function ($routeProvider) {
    gdufApp.config.router.forEach(function (item) {
        $routeProvider.when(item.uri,{
            templateUrl : item.templateUrl,
            controller : item.controller
        });
    });
});

//主控制器
gdufApp.controller('mainController', function($scope, $uibModal,$http) {
    $scope.menuData = [
        {'text':'网贷计算器', 'url':'#/calculate' , 'icon':'glyphicon glyphicon-home'},
    ];
    //该变量用于展示面包屑
    $scope.currentMenu = {};

    //对面包屑中的菜单信息做初始化（仅支持菜单的深度为2级，没有做多级的递归）
    $scope.initCurrentMenu = function () {
        var path = location.href.split('#')[1];
        if(!path) return;

        path = '#' + path;
        $scope.menuData.forEach(function (menu) {
            var children = menu.children;

            if(menu.url === path){
                $scope.setCurrentMenu(menu, null);
                return false;
            };

            if(!children) return true;

            for(var i = 0; i < children.length; i++){
                var child = children[i];
                if(child.url != path) continue;

                $scope.setCurrentMenu(menu, child);
                return false;
            }
        })
    }

    $scope.setCurrentMenu = function(parent, child){
        $scope.currentMenu = {
            parent : !!parent ? parent : null,
            child : !!child ? child : null
        };
    }

    $scope.openDialog = function (size, html, ctrl) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: html,
            controller: ctrl,
            size: size,
            scope : this
        });
        modalInstance.result.then(function (msg) {

        }, function (msg) {

        });
   };

    $scope.alert = function(msg){
        var modalInstance = $uibModal.open({
          animation: true,
          templateUrl: 'alert.html',
          controller: 'alertController',
          size: 'sm',
          resolve : {
            msg : function(){
                return msg;
            }
          }
       });
  }

   $scope.confirm = function(msg, yes ,no){
        $scope.confirmYes = yes;
        $scope.confirmNo = no;
        var modalInstance = $uibModal.open({
          animation: true,
          templateUrl: 'confirm.html',
          controller: 'confirmController',
          size: 'sm',
          scope : $scope,
          resolve : {
            msg : function(){
                return msg;
            }
          }
       });
  };

/**
 *  桌面通知
 */
  $scope.notify = function () {
      var eventSource = new EventSource("/notification/stream"),
           notification = null;
      eventSource.addEventListener('message', function (event) {
          var data = JSON.parse(event.data),
              str = '';
          for(var key in data){
              str += key + '  :  ' + data[key] + '\n';
          }

          if(Notification.permission !== 'granted'){
              $scope.alert('请开启桌面通知的功能，才能收到远程服务器中系统参数的更新结果');
              return;
          }

          notification = new Notification('提醒',{body:str});
      });
  };

  //页面初始化要执行的方法添加到init内部
  $scope.init = function () {
      $scope.initCurrentMenu();

      $scope.menuData.forEach(function (menu) {
          if (menu.url) {
              menu.url = location.href.split('#')[0] + menu.url;
          }

          if (menu.children) {
              for (var i = 0; i < menu.children.length; i++) {
                  var child = menu.children[i];
                  if (child && child.url) {
                      child.url = location.href.split('#')[0] + child.url;
                  }
              }
          }
      });

      if("Notification" in window){
          Notification.requestPermission();
          $scope.notify();
      }else{
          $scope.alert("您的浏览器可能不支持H5的高级特性,请更换Chrome浏览器");
      }
  }


  $scope.init();
});

//alert组件的控制器
gdufApp.controller('alertController', function($scope, $uibModalInstance, msg){
    $scope.msg = msg;

    $scope.ok = function(){
        $uibModalInstance.close('close');
    }

    $scope.cancel = function(){
        $uibModalInstance.dismiss('cancel');
    }
});

//confirm组件的控制器
gdufApp.controller('confirmController', function($scope, $uibModalInstance, msg){
    $scope.msg = msg;

    $scope.ok = function(){
        $scope.confirmYes();
        $uibModalInstance.close();
    }

    $scope.cancel = function(){
        $scope.confirmNo();
        $uibModalInstance.dismiss();
    }
});


//以下是自定义指令
//左侧菜单栏指令
gdufApp.directive('sidebar', function(){
    return {
        restrict : 'E',
        transclude : false,
        replace : true,
        templateUrl : 'pages/sidebar.html',
        link : function($scope, element){
            //在DOM渲染后，对DOM绑定相关交互事件，InitiateSideMenu是beyond.js中的全局方法
            InitiateSideMenu();
        }
    }
});

//顶部导航指令
gdufApp.directive('navbar', function(){
    return {
        restrict : 'E',
        transclude : true,
        replace : true,
        scope : {
            user : "=",
            logout : "&"
        },
        templateUrl : 'pages/navbar.html'
    }
});