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

  // saveComment(c: Comment) {
  //   const headers = new HttpHeaders().set(
  //     'Content-Type',
  //     'application/json; charset=utf-8'
  //   );

  //   // save as JSON file in request body
  //   const body = JSON.stringify(c);
  //   console.log('>>>> saveComment body: ', body);

  //   return lastValueFrom(
  //     this.http.post<Comment>('/api/comment', body, { headers: headers })
  //   );
  // }

  saveComment(title: string, name: string, rating: string, comment: string) {
    const headers = new HttpHeaders().set(
      'Content-Type',
      'application/x-www-form-urlencoded'
    );

    const body = new HttpParams()
      .set('title', title)
      .set('name', name)
      .set('rating', rating)
      .set('comment', comment);

    return lastValueFrom(
      this.http.post<Comment>(
        '/api/comment',
        body,
        { headers: headers }
      )
    );
  }
}
