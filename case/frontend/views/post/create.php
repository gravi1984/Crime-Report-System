<?php
/**
 * Created by PhpStorm.
 * User: DennyLee
 * Date: 2018/8/26
 * Time: 15:41
 */

use yii\bootstrap\ActiveForm;
use yii\bootstrap\Html;

$this->title = 'CreateCase';
$this->params['breadcrumbs'][] =['label'=>'Case','url'=>['post/index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="row">
    <div class="col-lg-9">
        <div class="panel-title box-title">
            <span>Create Case</span>
        </div>
        <div class="panel-body">
            <?php $form = ActiveForm::begin()?>
            <?=$form->field($model,'title')->textInput(['maxlength'=>true]) ?>
            <?=$form->field($model,'cat_id')->dropDownList($cat) ?>
            <?php $form = ActiveForm::begin(); ?>
            <?= $form->field($model, 'label_img')->widget('common\widgets\file_upload\FileUpload',[
                'config'=>[
                    //图片上传的一些配置，不写调用默认配置
                    'domain_url' => 'http://www.yii-china.com',]
            ]) ?>
            <?php ActiveForm::end(); ?>
<!--            --><?//=$form->field($model,'content')->textInput(['maxlength'=>true]) ?>
            <?= $form->field($model, 'content')->widget('common\widgets\ueditor\Ueditor',[
                'options'=>[
//                    'initialFrameWidth' => 850,
                    'initialFrameHeight' => 400,
                    'toolbars'=>[],
                ]
            ]) ?>
            <?=$form->field($model,'tags')->textInput(['maxlength'=>true]) ?>
            <div class="form-group">
                <?=Html::submitButton("Submit",['class'=>'btn btn-success'])?>
            </div>
            <?php ActiveForm::end()?>
        </div>
    </div>
    <div class="col-lg-3">
        <div class="panel-title box-title">
            <span>Notice</span>
            <p>1.1111111111</p>
        </div>
        <div>

        </div>
    </div>
</div>
