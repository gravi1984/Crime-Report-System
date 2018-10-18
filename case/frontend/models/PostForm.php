<?php
/**
 * Created by PhpStorm.
 * User: DennyLee
 * Date: 2018/8/26
 * Time: 15:20
 */
namespace frontend\models;
use yii\base\Model;

class PostForm extends Model{
    public $id;
    public $title;
    public $content;
    public $label_img;
    public $cat_id;
    public $tags;

    public $_lastError="";

    public function rules()
    {
        return [
            [['id','title','content','cat_id'],'required'],
            [['id','cat_id'],'integer'],
            ['title','string','min'=>4,'max'=>50],
        ];
    }

    public function attributeLabels()
    {
        return [
            'id'=>'CaseID',
            'title'=>'Title',
            'content'=>'Content',
            'label_img'=>'Image',
            'tags'=>'Tags',
            'cat_id'=>'Category',
        ];
    }
}