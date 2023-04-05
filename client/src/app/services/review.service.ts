import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { Review } from '../models/review';
import { Comment } from '../models/comment';

@Injectable({
  providedIn: 'root',
})
export class ReviewService {
  constructor(private http: HttpClient) {}

  searchReviews(movieName: string): Promise<any> {
    const params = new HttpParams().set('movieName', movieName);

    const headers = new HttpHeaders().set(
      'Content-Type',
      'application/json; charset=utf-8'
    );
    console.log('[getCharacters] >>> params = ' + params);
    console.log('[getCharacters] >>> headers = ' + headers);

    return lastValueFrom(
      this.http.get<Review[]>('/api/search', { params: params })
    );
  }

  saveComment(c: Comment) {
    const headers = new HttpHeaders().set(
      'Content-Type',
      'application/json; charset=utf-8'
    );
    const body = JSON.stringify(c);

    return lastValueFrom(
      this.http.post<Comment>('/api/comment', { headers: headers })
    );
  }
}
