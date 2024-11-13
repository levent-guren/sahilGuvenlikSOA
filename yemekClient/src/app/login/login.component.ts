import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  form!: FormGroup;

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private loginService: LoginService,
    private toastr: ToastrService
  ) {
    this.form = this.fb.group({
      username: [''],
      password: ['']
    });
  }
  submit() {
    const username = this.form.value.username;
    const password = this.form.value.password;
    this.loginService.login(username, password).subscribe({
      next: (data) => {
        // Set the token in local storage
        localStorage.setItem('token', data.token);
        // Navigate to the menu page
        this.router.navigate(['/menu']);
      },
      error: (err) => {
        this.toastr.error('Giriş başarısız!');
        console.error(err);
      }
    });
  }
}
