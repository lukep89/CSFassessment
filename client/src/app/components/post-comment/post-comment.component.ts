import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Comment } from 'src/app/models/comment';
import { ReviewService } from 'src/app/services/review.service';

@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css'],
})
export class PostCommentComponent implements OnInit {
  form!: FormGroup;
  param$!: Subscription;
  movieName!: string;

  constructor(
    private activatedRoute: ActivatedRoute,
    private reviewSvc: ReviewService,
    private router: Router,
    private fb: FormBuilder
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
      ]),
      rating: this.fb.control<string>('1', [Validators.required]),
      comment: this.fb.control<string>('', [Validators.required]),
    });
  }

  post() {
    const comment: Comment = {
      name: this.form.get('name')?.value,
      rating: this.form.get('rating')?.value,
      comment: this.form.get('comment')?.value,
      title: this.movieName,
    };

    this.reviewSvc.saveComment(comment);

    this.form.reset;
  }
}
