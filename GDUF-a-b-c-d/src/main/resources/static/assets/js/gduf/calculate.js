gdufApp.controller('calculateController', function ($scope, $uibModal, $http) {

    $scope.params = {
        'loanDate': null,
        'remainingBalance': null,
        'numberPayment': null,
        'numberPaymentType': 0,
        'rate': null,
        'rateType': 0,
        'repayType': null
    };

    $scope.scheduleList = [];

    $scope.repayTypes = [{
        key: '月还息到期还本',
        value: 0
    }, {
        key: '到期还本息',
        value: 1
    }, {
        key: '按月还本息（等额本息）',
        value: 2
    }, {
        key: '按日算息，每月付息，到期还本',
        value: 3
    }, {
        key: '等额本金',
        value: 4
    }];
    $scope.dateFormat = function (date) {
        var addZeroChar = function (str) {
            str += "";
            return str.length === 1 ? "0" + str : str;
        }
        var formatMilliseconds = function (str) {
            if (str == null) {
                return "000";
            }
            str += "";
            if (str.length == 1) {
                return "00" + str;
            }
            if (str.length == 2) {
                return "0" + str;
            }
            return str;
        }

        return date.getFullYear() + '-' + addZeroChar((date.getMonth() + 1 )) + '-' +
            addZeroChar(date.getDate()) + ' ' + addZeroChar(date.getHours()) + ':' +
            addZeroChar(date.getMinutes()) + ':' + addZeroChar(date.getSeconds()) + "." + formatMilliseconds(date.getMilliseconds());
    }
    $scope.dateToString = function (date) {
        if (date == null) {
            return null;
        }
        var d = new Date(date.getTime());
        var dd = d.getDate() < 10 ? "0" + d.getDate() : d.getDate().toString();
        var mm = (d.getMonth() + 1) < 10 ? "0" + (d.getMonth() + 1) : (d.getMonth() + 1).toString();
        var yyyy = d.getFullYear().toString();
        var HH = d.getHours() < 10 ? "0" + d.getHours() : d.getHours().toString();
        var MM = d.getMinutes() < 10 ? "0" + d.getMinutes() : d.getMinutes().toString();
        var ss = d.getSeconds() < 10 ? "0" + d.getSeconds() : d.getSeconds().toString();
        return yyyy + "-" + mm + "-" + dd;
    }

    $scope.params.loanDate = $scope.dateToString(new Date());

    $('#datetimepickerstart').datetimepicker();
    // 生成投资计划
    $scope.calculate = function () {
        $http({
            url: '/calc/InFine',
            method: 'POST',
            dataType: 'json',
            data: $scope.params
        }).success(function (data) {
                $scope.scheduleList = data;
        }).error(function (data) {
            $scope.alert('出错');
        });
    }
});