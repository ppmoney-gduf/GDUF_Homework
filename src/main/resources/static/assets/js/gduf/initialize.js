(function() {
    //js根路径
    var basePath = "assets/js";
    //加载单个JS文件
    var addJSFile = function(src, callback) {
        var head = document.getElementsByTagName("head")[0];
        var script = document.createElement("script");
        script.type = "text/javascript";
        script.onreadystatechange = function() {
            if (this.readyState == "loaded" || this.readyState == "complete") {
                if (typeof callback !== "undefined") {
                    callback()
                }
            }
        };
        script.onload = function() {
            if (typeof callback !== "undefined") {
                callback()
            }
        };
        script.src = basePath + '/' + src;
        head.appendChild(script)
    };

    //加载多个JS文件
    var addJSFiles = function(srcs, callback) {
        var addSingle = function(index) {
            if (index >= srcs.length) {
                return
            }
            if (index == srcs.length - 1 && typeof callback !== "undefined") {
                callback()
            }
            addJSFile(srcs[index],
                function() {
                    addSingle(index + 1)
                })
        };
        addSingle(0, callback)
    };
    
    var addCssFile  = function(url){
        
    }

    //此处配置js文件
    var srcs = [
        //Basic Scripts
        'jquery-2.0.3.min.js', 'bootstrap.min.js', 'skins.min.js',
        //Beyond Scripts
        'beyond.js',
        //mock
        'mock/mock-min.js', 'mock/mock.angular.js',
        //angular
        'angular/angular.min.1.5.8.js','angular/angular-route.min.js', 'angular/ui-bootstrap-tpls-1.3.1.min.js',
        //bootstrap
        'angular/bootstrap-datetimepicker.js',
        //business
        'gduf/app.js','gduf/calculate.js'];
    addJSFiles(srcs);
})();