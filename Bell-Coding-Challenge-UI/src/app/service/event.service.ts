import { Injectable } from '@angular/core'
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ApiResponse } from '../interface/apiresponse.interface';
import { MediaType } from '../interface/mediatype.interface';

@Injectable({
    providedIn: 'root',
})
export class EventService {

    // base url of api
    baseURL: string = 'http://localhost:8080/api/media/';
    mediaType: MediaType;

    constructor(public http: HttpClient) {
    }

    // GET the list of all media
    getMediaList(pageNo) {
        let subURL = 'getMediaList?top=';
        let url = this.baseURL + subURL+ pageNo ;
        return new Promise((resolve, reject) => {
            this.http.get(url).subscribe((res: ApiResponse) => {
                resolve(res);
            }, error => {
                reject(error);
            })
        })

    }

    // GET media details by media id
    getMediaDetailById(mediaId) {
        let subURL = 'getMediaDetailsById/' + mediaId;
        let url = this.baseURL + subURL;
        return new Promise((resolve, reject) => {
            this.http.get(url).subscribe((res: ApiResponse) => {
                resolve(res);
            }, error => {
                reject(error);
            })
        })
    }

    // GET metail by media id and image type
    getMediaDetailsByMediaIdAndImageType(requestBody) {
        let subURL = 'getMediaDetailsByIdAndType';
        let url = this.baseURL + subURL;
        return new Promise((resolve, reject) => {
            this.http.post(url, requestBody).subscribe((res) => {
                resolve(res);
            }, error => {
                reject(error);
            })
        })
    }

}


