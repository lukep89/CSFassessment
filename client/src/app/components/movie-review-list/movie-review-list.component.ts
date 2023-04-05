import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Review } from 'src/app/models/review';
import { ReviewService } from 'src/app/services/review.service';

@Component({
  selector: 'app-movie-review-list',
  templateUrl: './movie-review-list.component.html',
  styleUrls: ['./movie-review-list.component.css'],
})
export class MovieReviewListComponent implements OnInit, OnDestroy {
  reviews!: Review[];
  param$!: Subscription;
  movieName!: string;

  // for testing
  // reviewListTest: Review[] = [
  //   {
  //     title: 'godfather',
  //     rating: '7 testing',
  //     byline: 'hello testing',
  //     headline: 'testing x 1',
  //     summary: 'testing x 2',
  //     reviewURL: 'testing x 3',
  //     image: 'testing x 4',
  //     commentCount: '7 testing',
  //   },
  //   {
  //     title: 'john wick',
  //     rating: '3 testing',
  //     byline: 'hello testing',
  //     headline: 'testing x 1',
  //     summary: 'testing x 2',
  //     reviewURL: 'testing x 3',
  //     image: 'testing x 4',
  //     commentCount: '3 testing',
  //   },
  // ];

  constructor(
    private activatedRoute: ActivatedRoute,
    private reviewSvc: ReviewService,
    private router: Router
  ) {}

  async ngOnInit(): Promise<void> {
    this.param$ = this.activatedRoute.params.subscribe(async (params) => {
      this.movieName = params['movieName'];
      this.reviews = await this.reviewSvc.searchReviews(this.movieName);
    });

    // for testing
    // this.reviews = this.reviewListTest;
  }

  ngOnDestroy(): void {
    this.param$.unsubscribe();
  }
}
