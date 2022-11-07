import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiResponse } from './interface/apiresponse.interface';
import { Image } from './interface/image.interface';
import { Media } from './interface/media.interface';
import { MediaDetail } from './interface/mediadetail.interface';
import { MediaType } from './interface/mediatype.interface';
import { EventService } from './service/event.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Bell-Angular-UI';

  listOfMedia: Array<Media>;
  mediaImages: Array<Image>;
  mediaDetails: MediaDetail = ({} as any) as MediaDetail;
  genres: string;
  selectedMedia: string = '';
  selectedMediaType: string = '';
  showSection: number = 1;
  mediaType: MediaType;
  form: FormGroup = new FormGroup({});
  isError:false;
  constructor(private eventService: EventService,private fb: FormBuilder) {
    this.form = fb.group({
      number: ['', [Validators.required, Validators.pattern("^[0-9]*$")]]
    })
  }
  ngOnInit() {
    this.getMediaList(5);
  }
  
  
  
  get f(){
    return this.form.controls;
  }
  getMediaList(pageNo) {
    this.eventService.getMediaList(pageNo).then((response: ApiResponse) => {
      if (response.status == 'OK') {
        this.mediaType = response.data;
        this.listOfMedia = this.mediaType.Items.length > 0 ? this.mediaType.Items : [];
      }else{
        console.log(response.error);
      }
    })
  }
  selectedMediaChange(mediaId) {
    this.selectedMediaType = '';
    this.eventService.getMediaDetailById(mediaId).then((response: ApiResponse) => {
      if (response.status == 'OK') {
        this.mediaDetails = response.data ? response.data : {};
        this.mediaImages = [... this.mediaDetails.Images];
        this.genres = this.mediaDetails.Genres.map(x => x.Name).join(",");
      }
    })
  }



  selectedMediaTypeChange(mediaType) {
    let payload = {
      id: this.selectedMedia,
      imageType: mediaType
    }
    this.eventService.getMediaDetailsByMediaIdAndImageType(payload).then((response: ApiResponse) => {
      if (response.status == 'OK') {
        this.mediaDetails = response.data ? response.data : {};
        this.genres = this.mediaDetails.Genres.map(x => x.Name).join(",");
      }
    })
  }

  getPosterByMediaId(mediaId){
    let payload = {
      id: mediaId,
      imageType:'poster'
    }
    this.eventService.getMediaDetailsByMediaIdAndImageType(payload).then((response: ApiResponse) => {
      if (response.status == 'OK') {
        this.mediaDetails = response.data ? response.data : {};
        this.genres = this.mediaDetails.Genres.map(x => x.Name).join(",");
      }
    })
  }

}
