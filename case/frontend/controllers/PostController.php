<?php
/**
 * Created by PhpStorm.
 * User: DennyLee
 * Date: 2018/8/26
 * Time: 14:43
 */
namespace frontend\controllers;

/**
 * 文章控制器 Post Controller
 */
use common\models\CatModel;
use frontend\models\PostForm;
use Yii;
use frontend\controllers\base\BaseController;

class PostController extends BaseController{
    /**
     * 文章列表 Post List
     * @return string
     */
    public function actionIndex()
    {
        return $this->render('index');
    }

    /**
     * 创建文章
     * @return string
     */
    public function actionCreate()
    {
        $model = new PostForm();
        $cat = CatModel::getAllCats();
        return $this->render('create',['model'=>$model,'cat'=>$cat]);
    }

    /**
     * 图片上传插件 file upload
     * @return array
     */
    public function actions()
    {
        return [
            'upload'=>[
                'class' => 'common\widgets\file_upload\UploadAction',     //这里扩展地址别写错
                'config' => [
                    'imagePathFormat' => "/image/{yyyy}{mm}{dd}/{time}{rand:6}",
                ]
            ],
            'ueditor'=>[
                 'class' => 'common\widgets\ueditor\UeditorAction',
                 'config'=>[
                     //上传图片配置
                    'imageUrlPrefix' => "", /* 图片访问路径前缀 */
                    'imagePathFormat' => "/image/{yyyy}{mm}{dd}/{time}{rand:6}", /* 上传保存路径,可以自定义保存路径和文件名格式 */
                 ]
            ],
        ];
    }
}