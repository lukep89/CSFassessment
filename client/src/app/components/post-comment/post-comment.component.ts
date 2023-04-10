import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Comment } from 'src/app/models/comment';
import { ReviewService } from 'src/app/services/review.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css'],
})
export class PostCommentComponent implements OnInit, OnDestroy {
  form!: FormGroup;
  param$!: Subscription;
  movieName!: string;

  constructor(
    private activatedRoute: ActivatedRoute,
    private reviewSvc: ReviewService,
    private router: Router,
    private fb: FormBuilder,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.form = this.createForm();

    this.param$ = this.activatedRoute.params.subscribe((params) => {
      this.movieName = params['movieName'];
    });
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [
        Validators.required,
        Validators.minLength(3),
        Validators.pattern(/[\S]/),
      ]),
      rating: this.fb.control<string>('1', [Validators.required]),
      comment: this.fb.control<string>('', [Validators.required]),
    });
  }

  // post() {
  //   const comment: Comment = {
  //     name: this.form.get('name')?.value,
  //     rating: this.form.get('rating')?.value,
  //     comment: this.form.get('comment')?.value,
  //     title: this.movieName,
  //   };
  //   console.log('>>>> post() comment: ', comment);

  //   this.reviewSvc.saveComment(comment);

  //   // this.form.reset;
  //   this.location.back();
  // }

  post() {
    const title = this.movieName;
    const name = this.form.get('name')?.value;
    const rating = this.form.get('rating')?.value;
    const comment = this.form.get('comment')?.value;

    console.log('>>>> post() values: ', title, name, rating, comment);

    this.reviewSvc.saveComment(title, name, rating, comment);

    this.location.back();
  }

  goBack() {
    this.location.back();
  }

  ngOnDestroy(): void {
    this.param$.unsubscribe();
  }
}
