import { Genres } from "./genres.interface";
import { Image } from "./image.interface";

export interface MediaDetail {
    Id: number;
    Name: string;
    Desc: string;
    ShortDesc: string;
    Genres:Array<Genres>;
    Images: Array<Image>;
}