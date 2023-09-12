import COS from "cos-js-sdk-v5";
import {YMDHMS} from "@/utils/format.js";

const Bucket = 'kjdl-1257508506'
const Region = 'ap-beijing'
const SecretId = 'AKIDGLBnAON7crrc7NN7dtufBSTFm35rOgkZ'
const SecretKey = 'bx3uWszqRpxpRovnPlskMjqiKgneGciZ'
const cos = new COS({SecretId, SecretKey})

export const uploadFile = (file, onProgress, onError, onSuccess) => {

    const Key = YMDHMS() + '-' + file.name
    cos.uploadFile({
        Bucket, Region, Key, Body: file, onProgress: onProgress
    }, (err) => {
        err ? onError(err) : onSuccess(Key)
    })
}

export const previewUrl = (Key) => {
    cos.getObjectUrl({
        Bucket, Region, Key,
    }, (err, data) => {
        if (err) return
        const url = data.Url
        window.open(url)
    })
}

export const downloadUrl = (Key, name) => {
    cos.getObjectUrl({
        Bucket, Region, Key,
    }, (err, data) => {
        if (err) return
        const downloadUrl = data.Url + (data.Url.indexOf('?') > -1 ? '&' : '?') + `response-content-disposition=attachment;filename=${name}`;
        window.open(downloadUrl)
    })
}
