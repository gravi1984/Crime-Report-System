<?php
/**
 * Created by PhpStorm.
 * User: DennyLee
 * Date: 2018/8/26
 * Time: 14:43
 */
namespace frontend\controllers\base;

use yii\web\Controller;

class BaseController extends Controller{
    public function beforeAction($action)
    {
        if(!parent::beforeAction($action)){
            return false;
        }
        return true;
    }
}